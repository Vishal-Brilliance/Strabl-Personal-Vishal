package com.strabl.service.product.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.CategoryDao;
import com.strabl.sdk.domain.dao.impl.CartDaoImpl;
import com.strabl.sdk.domain.dao.impl.CategoryDaoImpl;
import com.strabl.sdk.domain.dao.impl.ProductDaoImpl;
import com.strabl.sdk.domain.dao.impl.UserDaoImpl;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import com.strabl.sdk.domain.repository.CartRepository;
import com.strabl.sdk.domain.repository.CategoryRepository;
import com.strabl.sdk.domain.repository.ClassificationRepository;
import com.strabl.sdk.domain.repository.ProductRepository;
import com.strabl.sdk.domain.repository.TBYBRepository;
import com.strabl.sdk.domain.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryDao categoryDao;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    void testFetchCategoriesBy() {

        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ProductRepository productRepository = mock(ProductRepository.class);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                null, userDao, new RedisTemplate());

        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
                new CategoryDaoImpl(categoryRepository, productDao, new UserDaoImpl(mock(UserRepository.class))));
        assertTrue(categoryServiceImpl.fetchCategoriesBy(OfferingType.TBYB, new PagedResponseRequest()).toList().isEmpty());
        verify(categoryRepository).findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFetchCategoriesBy2() {

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("Test@123");
        user.setPhone_number("4105551212");
        user.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        user.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        user1.setEmail("jane.doe@example.org");
        user1.setFullName("Dr Jane Doe");
        user1.setId(1);
        user1.setIsDeleted(true);
        user1.setIsEnabled(true);
        user1.setIsseller(true);
        user1.setPassword("Test@123");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category = new Category();
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        category.setCreatedBy(user);
        category.setDescription("The characteristics of someone or something");
        category.setIcon("Icon");
        category.setId(1);
        category.setImage("Image");
        category.setIsDeleted(true);
        category.setIsHome(true);
        category.setIsPublished(true);
        category.setIsRandomStuff(true);
        category.setIsRental(true);
        category.setIsTbyb(true);
        category.setName("Name");
        category.setOrderIndex(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(categoryList));
        ProductRepository productRepository = mock(ProductRepository.class);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                null, userDao, new RedisTemplate());

        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
                new CategoryDaoImpl(categoryRepository, productDao, new UserDaoImpl(mock(UserRepository.class))));
        assertEquals(1,
                categoryServiceImpl.fetchCategoriesBy(OfferingType.TBYB, new PagedResponseRequest()).toList().size());
        verify(categoryRepository).findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFetchCategoriesBy3() {

        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ProductRepository productRepository = mock(ProductRepository.class);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                null, userDao, new RedisTemplate());

        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
                new CategoryDaoImpl(categoryRepository, productDao, new UserDaoImpl(mock(UserRepository.class))));
        assertTrue(
                categoryServiceImpl.fetchCategoriesBy(OfferingType.RENTAL, new PagedResponseRequest()).toList().isEmpty());
        verify(categoryRepository).findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFetchCategoriesBy4() {
        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("Test@123");
        user.setPhone_number("4105551212");
        user.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        user.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        user1.setEmail("jane.doe@example.org");
        user1.setFullName("Dr Jane Doe");
        user1.setId(1);
        user1.setIsDeleted(true);
        user1.setIsEnabled(true);
        user1.setIsseller(true);
        user1.setPassword("Test@123");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category = new Category();
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        category.setCreatedBy(user);
        category.setDescription("The characteristics of someone or something");
        category.setIcon("Icon");
        category.setId(1);
        category.setImage("Image");
        category.setIsDeleted(true);
        category.setIsHome(true);
        category.setIsPublished(true);
        category.setIsRandomStuff(true);
        category.setIsRental(true);
        category.setIsTbyb(true);
        category.setName("Name");
        category.setOrderIndex(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");

        User user2 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant());
        user2.setEmail("jane.doe@example.org");
        user2.setFullName("Dr Jane Doe");
        user2.setId(1);
        user2.setIsDeleted(true);
        user2.setIsEnabled(true);
        user2.setIsseller(true);
        user2.setPassword("Test@123");
        user2.setPhone_number("4105551212");
        user2.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant());
        user2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user3 = new User();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
        user3.setEmail("jane.doe@example.org");
        user3.setFullName("Dr Jane Doe");
        user3.setId(1);
        user3.setIsDeleted(true);
        user3.setIsEnabled(true);
        user3.setIsseller(true);
        user3.setPassword("Test@123");
        user3.setPhone_number("4105551212");
        user3.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category1 = new Category();
        category1.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category1.setCreatedAt(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        category1.setCreatedBy(user2);
        category1.setDescription("The characteristics of someone or something");
        category1.setIcon("Icon");
        category1.setId(1);
        category1.setImage("Image");
        category1.setIsDeleted(true);
        category1.setIsHome(true);
        category1.setIsPublished(true);
        category1.setIsRandomStuff(true);
        category1.setIsRental(true);
        category1.setIsTbyb(true);
        category1.setName("Name");
        category1.setOrderIndex(1);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category1.setUpdatedAt(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        category1.setUpdatedBy(user3);
        category1.setUrl("https://example.org/example");

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(categoryList));
        ProductRepository productRepository = mock(ProductRepository.class);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                null, userDao, new RedisTemplate());

        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
                new CategoryDaoImpl(categoryRepository, productDao, new UserDaoImpl(mock(UserRepository.class))));
        assertEquals(2,
                categoryServiceImpl.fetchCategoriesBy(OfferingType.TBYB, new PagedResponseRequest()).toList().size());
        verify(categoryRepository).findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFetchCategoriesBy5() {

        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ProductRepository productRepository = mock(ProductRepository.class);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                null, userDao, new RedisTemplate());

        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
                new CategoryDaoImpl(categoryRepository, productDao, new UserDaoImpl(mock(UserRepository.class))));
        assertTrue(categoryServiceImpl.fetchCategoriesBy(OfferingType.TBYB, new PagedResponseRequest()).toList().isEmpty());
        verify(categoryRepository).findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFetchCategoriesBy6() {

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("Test@123");
        user.setPhone_number("4105551212");
        user.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        user.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        user1.setEmail("jane.doe@example.org");
        user1.setFullName("Dr Jane Doe");
        user1.setId(1);
        user1.setIsDeleted(true);
        user1.setIsEnabled(true);
        user1.setIsseller(true);
        user1.setPassword("Test@123");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category = new Category();
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        category.setCreatedBy(user);
        category.setDescription("The characteristics of someone or something");
        category.setIcon("Icon");
        category.setId(1);
        category.setImage("Image");
        category.setIsDeleted(true);
        category.setIsHome(true);
        category.setIsPublished(true);
        category.setIsRandomStuff(true);
        category.setIsRental(true);
        category.setIsTbyb(true);
        category.setName("Name");
        category.setOrderIndex(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(categoryList));
        ProductRepository productRepository = mock(ProductRepository.class);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                null, userDao, new RedisTemplate());

        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
                new CategoryDaoImpl(categoryRepository, productDao, new UserDaoImpl(mock(UserRepository.class))));
        assertEquals(1,
                categoryServiceImpl.fetchCategoriesBy(OfferingType.TBYB, new PagedResponseRequest()).toList().size());
        verify(categoryRepository).findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFetchCategoriesBy7() {

        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ProductRepository productRepository = mock(ProductRepository.class);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                null, userDao, new RedisTemplate());

        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
                new CategoryDaoImpl(categoryRepository, productDao, new UserDaoImpl(mock(UserRepository.class))));
        assertTrue(
                categoryServiceImpl.fetchCategoriesBy(OfferingType.RENTAL, new PagedResponseRequest()).toList().isEmpty());
        verify(categoryRepository).findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFetchCategoriesBy8() {

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("Test@123");
        user.setPhone_number("4105551212");
        user.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        user.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        user1.setEmail("jane.doe@example.org");
        user1.setFullName("Dr Jane Doe");
        user1.setId(1);
        user1.setIsDeleted(true);
        user1.setIsEnabled(true);
        user1.setIsseller(true);
        user1.setPassword("Test@123");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category = new Category();
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        category.setCreatedBy(user);
        category.setDescription("The characteristics of someone or something");
        category.setIcon("Icon");
        category.setId(1);
        category.setImage("Image");
        category.setIsDeleted(true);
        category.setIsHome(true);
        category.setIsPublished(true);
        category.setIsRandomStuff(true);
        category.setIsRental(true);
        category.setIsTbyb(true);
        category.setName("Name");
        category.setOrderIndex(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");

        User user2 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant());
        user2.setEmail("jane.doe@example.org");
        user2.setFullName("Dr Jane Doe");
        user2.setId(1);
        user2.setIsDeleted(true);
        user2.setIsEnabled(true);
        user2.setIsseller(true);
        user2.setPassword("Test@123");
        user2.setPhone_number("4105551212");
        user2.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant());
        user2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user3 = new User();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
        user3.setEmail("jane.doe@example.org");
        user3.setFullName("Dr Jane Doe");
        user3.setId(1);
        user3.setIsDeleted(true);
        user3.setIsEnabled(true);
        user3.setIsseller(true);
        user3.setPassword("Test@123");
        user3.setPhone_number("4105551212");
        user3.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category1 = new Category();
        category1.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category1.setCreatedAt(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        category1.setCreatedBy(user2);
        category1.setDescription("The characteristics of someone or something");
        category1.setIcon("Icon");
        category1.setId(1);
        category1.setImage("Image");
        category1.setIsDeleted(true);
        category1.setIsHome(true);
        category1.setIsPublished(true);
        category1.setIsRandomStuff(true);
        category1.setIsRental(true);
        category1.setIsTbyb(true);
        category1.setName("Name");
        category1.setOrderIndex(1);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category1.setUpdatedAt(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        category1.setUpdatedBy(user3);
        category1.setUrl("https://example.org/example");

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(categoryList));
        ProductRepository productRepository = mock(ProductRepository.class);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                null, userDao, new RedisTemplate());

        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
                new CategoryDaoImpl(categoryRepository, productDao, new UserDaoImpl(mock(UserRepository.class))));
        assertEquals(2,
                categoryServiceImpl.fetchCategoriesBy(OfferingType.TBYB, new PagedResponseRequest()).toList().size());
        verify(categoryRepository).findAll((org.springframework.data.jpa.domain.Specification<Category>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetByFilterCategory() {

        PageImpl<ProductResponse> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.categoryDao.getByFilterCategory(anyBoolean(), anyBoolean(), (PagedResponseRequest) any()))
                .thenReturn(pageImpl);
        Page<ProductResponse> actualByFilterCategory = this.categoryServiceImpl.getByFilterCategory(true, true,
                new PagedResponseRequest());
        assertSame(pageImpl, actualByFilterCategory);
        assertTrue(actualByFilterCategory.toList().isEmpty());
        verify(this.categoryDao).getByFilterCategory(anyBoolean(), anyBoolean(), (PagedResponseRequest) any());
    }

    @Test
    void testGetByFilterCategory2() {

        when(this.categoryDao.getByFilterCategory(anyBoolean(), anyBoolean(), (PagedResponseRequest) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class,
                () -> this.categoryServiceImpl.getByFilterCategory(true, true, new PagedResponseRequest()));
        verify(this.categoryDao).getByFilterCategory(anyBoolean(), anyBoolean(), (PagedResponseRequest) any());
    }

    @Test
    void testGetByFilterCategory3() {

        PageImpl<ProductResponse> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.categoryDao.getByFilterCategory(anyBoolean(), anyBoolean(), (PagedResponseRequest) any()))
                .thenReturn(pageImpl);
        Page<ProductResponse> actualByFilterCategory = this.categoryServiceImpl.getByFilterCategory(true, true,
                new PagedResponseRequest());
        assertSame(pageImpl, actualByFilterCategory);
        assertTrue(actualByFilterCategory.toList().isEmpty());
        verify(this.categoryDao).getByFilterCategory(anyBoolean(), anyBoolean(), (PagedResponseRequest) any());
    }

    @Test
    void testGetByFilterCategory4() {

        when(this.categoryDao.getByFilterCategory(anyBoolean(), anyBoolean(), (PagedResponseRequest) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class,
                () -> this.categoryServiceImpl.getByFilterCategory(true, true, new PagedResponseRequest()));
        verify(this.categoryDao).getByFilterCategory(anyBoolean(), anyBoolean(), (PagedResponseRequest) any());
    }

    @Test
    void testAddCategory() {

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("Test@123");
        user.setPhone_number("4105551212");
        user.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        user.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        user1.setEmail("jane.doe@example.org");
        user1.setFullName("Dr Jane Doe");
        user1.setId(1);
        user1.setIsDeleted(true);
        user1.setIsEnabled(true);
        user1.setIsseller(true);
        user1.setPassword("Test@123");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category = new Category();
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        category.setCreatedBy(user);
        category.setDescription("The characteristics of someone or something");
        category.setIcon("Icon");
        category.setId(1);
        category.setImage("Image");
        category.setIsDeleted(true);
        category.setIsHome(true);
        category.setIsPublished(true);
        category.setIsRandomStuff(true);
        category.setIsRental(true);
        category.setIsTbyb(true);
        category.setName("Name");
        category.setOrderIndex(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");
        when(this.categoryDao.addCategory((Category) any(), (Integer) any())).thenReturn(category);
        CategoryDTO categoryDTO = mock(CategoryDTO.class);
        when(categoryDTO.getIsHome()).thenReturn(true);
        when(categoryDTO.getIsPublished()).thenReturn(true);
        when(categoryDTO.getIsRandomStuff()).thenReturn(true);
        when(categoryDTO.getIsRental()).thenReturn(true);
        when(categoryDTO.getIsTbyb()).thenReturn(true);
        when(categoryDTO.getCreatedBy()).thenReturn(1);
        when(categoryDTO.getOrderIndex()).thenReturn(1);
        when(categoryDTO.getUpdatedBy()).thenReturn(1);
        when(categoryDTO.getCategoryUrl()).thenReturn("https://example.org/example");
        when(categoryDTO.getDescription()).thenReturn("The characteristics of someone or something");
        when(categoryDTO.getIcon()).thenReturn("Icon");
        when(categoryDTO.getImage()).thenReturn("Image");
        when(categoryDTO.getName()).thenReturn("Name");
        when(categoryDTO.getUrl()).thenReturn("https://example.org/example");
        assertSame(category, this.categoryServiceImpl.addCategory(categoryDTO, 123));
        verify(this.categoryDao).addCategory((Category) any(), (Integer) any());
        verify(categoryDTO, atLeast(1)).getIsHome();
        verify(categoryDTO, atLeast(1)).getIsPublished();
        verify(categoryDTO, atLeast(1)).getIsRandomStuff();
        verify(categoryDTO).getIsRental();
        verify(categoryDTO).getIsTbyb();
        verify(categoryDTO).getCreatedBy();
        verify(categoryDTO).getOrderIndex();
        verify(categoryDTO).getUpdatedBy();
        verify(categoryDTO).getCategoryUrl();
        verify(categoryDTO).getDescription();
        verify(categoryDTO).getIcon();
        verify(categoryDTO).getImage();
        verify(categoryDTO).getName();
        verify(categoryDTO).getUrl();
    }

    @Test
    void testAddCategory2() {

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("Test@123");
        user.setPhone_number("4105551212");
        user.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        user.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        user1.setEmail("jane.doe@example.org");
        user1.setFullName("Dr Jane Doe");
        user1.setId(1);
        user1.setIsDeleted(true);
        user1.setIsEnabled(true);
        user1.setIsseller(true);
        user1.setPassword("Test@123");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category = new Category();
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        category.setCreatedBy(user);
        category.setDescription("The characteristics of someone or something");
        category.setIcon("Icon");
        category.setId(1);
        category.setImage("Image");
        category.setIsDeleted(true);
        category.setIsHome(true);
        category.setIsPublished(true);
        category.setIsRandomStuff(true);
        category.setIsRental(true);
        category.setIsTbyb(true);
        category.setName("Name");
        category.setOrderIndex(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");
        when(this.categoryDao.addCategory((Category) any(), (Integer) any())).thenReturn(category);
        CategoryDTO categoryDTO = mock(CategoryDTO.class);
        when(categoryDTO.getIsHome()).thenReturn(true);
        when(categoryDTO.getIsPublished()).thenReturn(true);
        when(categoryDTO.getIsRandomStuff()).thenReturn(true);
        when(categoryDTO.getIsRental()).thenReturn(true);
        when(categoryDTO.getIsTbyb()).thenReturn(true);
        when(categoryDTO.getCreatedBy()).thenReturn(1);
        when(categoryDTO.getOrderIndex()).thenReturn(1);
        when(categoryDTO.getUpdatedBy()).thenReturn(1);
        when(categoryDTO.getCategoryUrl()).thenReturn("https://example.org/example");
        when(categoryDTO.getDescription()).thenReturn("The characteristics of someone or something");
        when(categoryDTO.getIcon()).thenReturn("Icon");
        when(categoryDTO.getImage()).thenReturn("Image");
        when(categoryDTO.getName()).thenReturn("Name");
        when(categoryDTO.getUrl()).thenReturn("https://example.org/example");
        assertSame(category, this.categoryServiceImpl.addCategory(categoryDTO, 123));
        verify(this.categoryDao).addCategory((Category) any(), (Integer) any());
        verify(categoryDTO, atLeast(1)).getIsHome();
        verify(categoryDTO, atLeast(1)).getIsPublished();
        verify(categoryDTO, atLeast(1)).getIsRandomStuff();
        verify(categoryDTO).getIsRental();
        verify(categoryDTO).getIsTbyb();
        verify(categoryDTO).getCreatedBy();
        verify(categoryDTO).getOrderIndex();
        verify(categoryDTO).getUpdatedBy();
        verify(categoryDTO).getCategoryUrl();
        verify(categoryDTO).getDescription();
        verify(categoryDTO).getIcon();
        verify(categoryDTO).getImage();
        verify(categoryDTO).getName();
        verify(categoryDTO).getUrl();
    }
}

