package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.criteria.page.StrablPageable;
import com.strabl.sdk.domain.criteria.specification.ProductSpecification;
import com.strabl.sdk.domain.dao.CartDao;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Cart;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.mapper.ProductMapper;
import com.strabl.sdk.domain.repository.ClassificationRepository;
import com.strabl.sdk.domain.repository.ProductRepository;
import com.strabl.sdk.domain.repository.TBYBRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductDaoImpl implements ProductDao {

  public static final String CART_HASH_KEY = "CART";
  public static final String PRODUCT_HASH_KEY = "PRODUCT";
  private final ProductRepository productRepository;
  private final CartDao cartDao;
  private final ClassificationRepository classificationRepository;
  private final TBYBRepository tbybRepository;
  private final EntityManager entityManager;
  private final UserDao userDao;
  private final RedisTemplate redisTemplate;

  @Override
  public Product findByIdOrThrow(Integer productId) {
    return productRepository
        .findById(productId)
        .orElseThrow(() -> StrablException.of(ResponseType.PRODUCT_NOT_FOUND));
  }

  @Override
  public Product findBySlug(String slug) {

    Specification<Product> specification = ProductSpecification.bySlug(slug);

    return productRepository
        .findOne(specification)
        .orElseThrow(() -> StrablException.of(ResponseType.PRODUCT_NOT_FOUND));
  }

  @Override
  public Product create(Product product) {

    if (product.getClassification() != null) {
      product.getClassification().setCreatedAt(Instant.now());
      product.getClassification().setUpdatedAt(Instant.now());
      classificationRepository.save(product.getClassification());
    }

    if (product.getTbyb() != null) {
      product.getTbyb().setCreatedAt(Instant.now());
      product.getTbyb().setUpdatedAt(Instant.now());
      tbybRepository.save(product.getTbyb());
    }

    product.setCreatedAt(Instant.now());
    product.setUpdatedAt(Instant.now());

    Product savedProduct = productRepository.save(product);
    entityManager.refresh(savedProduct);
    return savedProduct;
  }

  @Override
  public Product update(Product product) {
    product.setUpdatedAt(Instant.now());
    return productRepository.save(product);
  }

  @Override
  public Page<Product> searchByOfferingType(OfferingType offeringType, PagedResponseRequest pagedResponseRequest) {

    Specification<Product> specification = ProductSpecification.byOfferingType(offeringType);
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

    return productRepository.findAll(specification, pageable);
  }

  @Override
  public Page<Product> searchByTagsAndTitle(String searchTerm, PagedResponseRequest pagedResponseRequest) {

    Specification<Product> specification = ProductSpecification.byTagAndTitle(searchTerm);
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

    return productRepository.findAll(specification, pageable);
  }

  @Override
  public Page<Product> getByCategory(String category, PagedResponseRequest pagedResponseRequest) {

    Specification<Product> specification = ProductSpecification.byCategory(category);
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

    return productRepository.findAll(specification, pageable);
  }

  @Override
  public Page<Product> getFeaturedRandomly(OfferingType offeringType, Integer count) {

    Page<Integer> ids;

    switch (offeringType) {

      case TBYB:
         ids = productRepository.getTBYBRandomFeaturedIds(PageRequest.of(0, count));
         return new PageImpl<>(productRepository.findAllById(ids));

      case RENTAL:
        ids = productRepository.getRentalRandomFeaturedIds(PageRequest.of(0, count));
        return new PageImpl<>(productRepository.findAllById(ids));

      default:
        return Page.empty();
    }
  }

  @Override
  public Page<Product> getUnapprovedProducts(PagedResponseRequest pagedResponseRequest) {

    Specification<Product> specification = ProductSpecification.pendingApproval();
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

    return productRepository.findAll(specification, pageable);
  }

  @Override
  public Product updateApprovalStatus(Integer productId, boolean approvalStatus) {

    Product product = this.findByIdOrThrow(productId);
    product.setIsApproved(approvalStatus);

    return productRepository.save(product);
  }

  @Override
  public void deleteProductById(Integer id) {
    productRepository.deleteById(id);
  }

  @Override
  public Page<Product> sellerProduct(Integer sellerId, PagedResponseRequest pagedResponseRequest) {
     Specification<Product> specification = ProductSpecification.sellerItems(sellerId);
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

    return productRepository.findAll(specification ,pageable);
  }

  @Override
  public List<Product> getAllProductByCategory(String category) {
    Specification<Product> specification = ProductSpecification.byCategory(category);
    return productRepository.findAll(specification);
  }

  @Override
  public void addProductToCart(Integer productId, Integer userId) {
    User user = userDao.findByIdOrThrow(userId);
    if (!user.getIsEnabled()) {
      throw StrablException.of(ResponseType.USER_NOT_VERIFIED_OR_ENABLED);
    }

    Product product = findByIdOrThrow(productId);

    Cart cart = getCartByUserIdFromRedis(userId);
    cart.setUpdatedAt(Instant.now());
    cart.addProductToCart(product);
    cartDao.save(cart);
    redisTemplate.opsForHash().put(CART_HASH_KEY, userId, cart);
  }

  @Override
  public Page<ProductResponse> getProductFromCart(Integer userId, PagedResponseRequest pagedResponseRequest) {
    User user = userDao.findByIdOrThrow(userId);
    if (!user.getIsEnabled()) {
      throw StrablException.of(ResponseType.USER_NOT_VERIFIED_OR_ENABLED);
    }
    Cart cart = getCartByUserIdFromRedis(userId);
    List<ProductResponse> productResponseList = new ArrayList<>();
    productResponseList = cart.getProductList().stream().map(ProductMapper::toProductResponse).collect(Collectors.toList());
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
    return new PageImpl<>(productResponseList, pageable, productResponseList.size());

  }

  @Override
  public void deleteProductFromCart(Integer productId, Integer userId) {
    User user = userDao.findByIdOrThrow(userId);

    if (!user.getIsEnabled()) {
      throw StrablException.of(ResponseType.USER_NOT_VERIFIED_OR_ENABLED);
    }

    Cart cart = getCartByUserIdFromRedis(userId);
    Product product = findByIdOrThrow(productId);
    cart.deleteProductFromCart(product);
    cartDao.save(cart);
    redisTemplate.opsForHash().put(CART_HASH_KEY, userId, cart);
  }

  private Cart getCartByUserIdFromRedis(Integer userId) {
    Cart cart = null;
    try {
      cart = (Cart) redisTemplate.opsForHash().get(CART_HASH_KEY, userId); // get cart from redis
    } catch (Exception e) {
      redisTemplate.delete(CART_HASH_KEY);
    }
    userDao.findByIdOrThrow(userId);
    if (cart == null) {
      // if there is no cart in Redis then fetch from db
      Cart cartInDb = cartDao.findByUserId(userId);
      if (cartInDb != null) {
        return cartInDb;
      }
      cart = new Cart();
      cart.setIsDeleted(false);
      cart.setUser(userDao.findByIdOrThrow(userId));
      cart.setCreatedAt(Instant.now());
      return cart;
    }
    return cart;
  }

  @Override
  public Page<ProductResponse> getSellerItems(Integer productId, PagedResponseRequest pagedResponseRequest) {
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
    Product product = findByIdOrThrow(productId);
    OfferingType offeringType = OfferingType.RENTAL;
    if (product.getTbyb() != null) {
      offeringType = OfferingType.TBYB;
    }
    Specification<Product> specification =
            ProductSpecification.byBrandTypeSubCategory(product.getBrand(), offeringType, product.getSubCategory().getName());

    List<Product> productList = new ArrayList<>();
    Set<ProductResponse> productResponseList = new HashSet<>();

    productList = productRepository.findAll(specification);

    Random r = new Random();

    for (int i = 0; i < 6; i++){
      int randomNumber= r.nextInt(productList.size());
      productResponseList.add(ProductMapper.toProductResponse(productList.get(randomNumber)));
    }

    return new PageImpl<>(new ArrayList<>(productResponseList), pageable, productResponseList.size());
  }

  @Override
  public Set<ProductResponse> getRecentlyAddedProducts(Integer userId) {
    LinkedList<Product> linkedListProduct = getListRecentProductInRedis(userId);
    List<Product> listProduct = new ArrayList<>(linkedListProduct);
    Collections.reverse(listProduct); // to get the latest product added
    return listProduct.stream().map(ProductMapper::toProductResponse).collect(Collectors.toSet());
  }

  @Override
  public void recentlyAddedProducts(Integer productId, Integer userId) {
    Product product = findByIdOrThrow(productId);

    LinkedList<Product> linkedListProduct = getListRecentProductInRedis(userId);
    if (linkedListProduct.size() <= 12) {
      if (product.getCategory().getIsTbyb()) {
        linkedListProduct.add(product);
      }
    } else {
      linkedListProduct.pop();
      if (product.getCategory().getIsTbyb()) {
        linkedListProduct.add(product);
      }
    }
    redisTemplate.opsForHash().put(PRODUCT_HASH_KEY, userId, linkedListProduct);
  }

  private LinkedList<Product> getListRecentProductInRedis(Integer userId) {
    LinkedList<Product> linkedListProduct = null;
    try {
      linkedListProduct = (LinkedList<Product>) redisTemplate.opsForHash().get(PRODUCT_HASH_KEY, userId);
    } catch (Exception e) {
      redisTemplate.delete(PRODUCT_HASH_KEY);
    }
    if (linkedListProduct == null) {
      linkedListProduct = new LinkedList<>();
    }
    return linkedListProduct;
  }

  @Override
  public Page<Product> getFromSubCategory(Integer subcategory_id, PagedResponseRequest pagedResponseRequest) {
    Specification<Product> specification = ProductSpecification.bySubCategory(subcategory_id);
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
    return productRepository.findAll(specification,pageable);
  }
}

