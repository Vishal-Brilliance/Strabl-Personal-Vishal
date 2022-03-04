package com.strabl.service.product.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.AddressDTO;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ClassificationDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.TBYBDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.domain.dao.impl.CartDaoImpl;
import com.strabl.sdk.domain.dao.impl.ProductDaoImpl;
import com.strabl.sdk.domain.dao.impl.UserDaoImpl;
import com.strabl.sdk.domain.entity.Address;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.Classification;
import com.strabl.sdk.domain.entity.Currency;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.TBYB;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.ClassificationType;
import com.strabl.sdk.domain.entity.enums.columns.TrialDaysEnum;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import com.strabl.sdk.domain.repository.CartRepository;
import com.strabl.sdk.domain.repository.ClassificationRepository;
import com.strabl.sdk.domain.repository.ProductRepository;
import com.strabl.sdk.domain.repository.TBYBRepository;
import com.strabl.sdk.domain.repository.UserRepository;
import com.strabl.service.product.service.ProductService;
import com.strabl.service.product.service.impl.ProductServiceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.engine.spi.SessionDelegatorBaseImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    @Test
    void testGetProductBySlug() {
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

        Address address = new Address();
        address.setArea("Area");
        address.setBuilding("Building");
        address.setCity("Oxford");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        address.setCreatedAt(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        address.setFlatNumber("42");
        address.setId(1);
        address.setIsDeleted(true);
        address.setStatus(true);
        address.setStreetName("Street Name");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        address.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        address.setUser(user);
        UUID randomUUIDResult = UUID.randomUUID();
        address.setUuid(randomUUIDResult);

        User user1 = new User();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        user1.setEmail("jane.doe@example.org");
        user1.setFullName("Dr Jane Doe");
        user1.setId(1);
        user1.setIsDeleted(true);
        user1.setIsEnabled(true);
        user1.setIsseller(true);
        user1.setPassword("Test@123");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

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

        Category category = new Category();
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
        category.setCreatedBy(user1);
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
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user2);
        category.setUrl("https://example.org/example");

        Classification classification = new Classification();
        classification.setClassificationType(ClassificationType.SHORT);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        classification.setCreatedAt(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        classification.setId(1);
        classification.setIsDeleted(true);
        classification.setIsFeatured(true);
        classification.setMinimumDays(1);
        classification.setMinimumMonths(1);
        classification.setRentLongTerm(1L);
        classification.setRentPerDay(1L);
        classification.setRentPerMonth(1L);
        classification.setRentPerWeek(1L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        classification.setUpdatedAt(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        classification.setUuid(UUID.randomUUID());

        Currency currency = new Currency();
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        currency.setCreatedAt(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant());
        currency.setCurrencyIso("GBP");
        currency.setCurrencyName("GBP");
        currency.setCurrencySymbol("GBP");
        currency.setId(1);
        currency.setIsDeleted(true);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        currency.setUpdatedAt(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant());
        currency.setUuid(UUID.randomUUID());

        User user3 = new User();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant());
        user3.setEmail("jane.doe@example.org");
        user3.setFullName("Dr Jane Doe");
        user3.setId(1);
        user3.setIsDeleted(true);
        user3.setIsEnabled(true);
        user3.setIsseller(true);
        user3.setPassword("Test@123");
        user3.setPhone_number("4105551212");
        user3.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant());
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user4 = new User();
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreatedAt(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant());
        user4.setEmail("jane.doe@example.org");
        user4.setFullName("Dr Jane Doe");
        user4.setId(1);
        user4.setIsDeleted(true);
        user4.setIsEnabled(true);
        user4.setIsseller(true);
        user4.setPassword("Test@123");
        user4.setPhone_number("4105551212");
        user4.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setUpdatedAt(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant());
        user4.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user5 = new User();
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user5.setCreatedAt(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant());
        user5.setEmail("jane.doe@example.org");
        user5.setFullName("Dr Jane Doe");
        user5.setId(1);
        user5.setIsDeleted(true);
        user5.setIsEnabled(true);
        user5.setIsseller(true);
        user5.setPassword("Test@123");
        user5.setPhone_number("4105551212");
        user5.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user5.setUpdatedAt(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant());
        user5.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user6 = new User();
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user6.setCreatedAt(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant());
        user6.setEmail("jane.doe@example.org");
        user6.setFullName("Dr Jane Doe");
        user6.setId(1);
        user6.setIsDeleted(true);
        user6.setIsEnabled(true);
        user6.setIsseller(true);
        user6.setPassword("Test@123");
        user6.setPhone_number("4105551212");
        user6.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user6.setUpdatedAt(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant());
        user6.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category1 = new Category();
        category1.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category1.setCreatedAt(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant());
        category1.setCreatedBy(user5);
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
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category1.setUpdatedAt(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant());
        category1.setUpdatedBy(user6);
        category1.setUrl("https://example.org/example");

        TBYB tbyb = new TBYB();
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        tbyb.setCreatedAt(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant());
        tbyb.setId(1);
        tbyb.setIsDeleted(true);
        tbyb.setTrialDays(TrialDaysEnum.FIVE);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        tbyb.setUpdatedAt(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant());
        tbyb.setUuid(UUID.randomUUID());

        Product product = new Product();
        product.setAddress(address);
        product.setBrand("Brand");
        product.setCartList(new ArrayList<>());
        product.setCategory(category);
        product.setClassification(classification);
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        product.setCreatedAt(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant());
        product.setCurrency(currency);
        product.setCustomer(user3);
        product.setDescription("The characteristics of someone or something");
        product.setFeatures("Features");
        product.setFinalPrice(1L);
        product.setId(1);
        product.setIsActive(true);
        product.setIsApproved(true);
        product.setIsDeleted(true);
        product.setIsFeatured(true);
        product.setSeller(user4);
        product.setSlug("Slug");
        product.setSubCategory(category1);
        product.setTags("Tags");
        product.setTbyb(tbyb);
        product.setTitle("Dr");
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        product.setUpdatedAt(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant());
        UUID randomUUIDResult1 = UUID.randomUUID();
        product.setUuid(randomUUIDResult1);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findOne((org.springframework.data.jpa.domain.Specification<Product>) any()))
                .thenReturn(Optional.of(product));
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        SessionDelegatorBaseImpl entityManager = new SessionDelegatorBaseImpl(mock(SessionDelegatorBaseImpl.class));
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                entityManager, userDao, new RedisTemplate());

        ResponseDTO<ProductResponse> actualProductBySlug = (new ProductController(mock(ProductRepository.class),
                new ProductServiceImpl(productDao, new UserDaoImpl(mock(UserRepository.class))))).getProductBySlug("Slug");
        assertEquals("2000", actualProductBySlug.getCode());
        assertEquals("Operation completed successfully", actualProductBySlug.getMessage());
        assertEquals(HttpStatus.OK, actualProductBySlug.getHttpStatus());
        ProductResponse data = actualProductBySlug.getData();
        assertSame(randomUUIDResult1, data.getUuid());
        assertEquals("Brand", data.getBrand());
        assertEquals("The characteristics of someone or something", data.getDescription());
        assertEquals("Slug", data.getSlug());
        assertEquals("Features", data.getFeatures());
        assertEquals("Tags", data.getTags());
        assertEquals(1L, data.getFinalPrice().longValue());
        assertEquals("Dr", data.getTitle());
        Instant expectedUpdatedAt = data.getCreatedAt();
        assertSame(expectedUpdatedAt, data.getUpdatedAt());
        assertEquals(1, data.getId().intValue());
        CategoryDTO expectedCategory = data.getSubCategory();
        assertEquals(expectedCategory, data.getCategory());
        assertTrue(data.getIsActive());
        assertTrue(data.getIsApproved());
        assertTrue(data.getIsFeatured());
        UserResponseDTO expectedSeller = data.getCustomer();
        assertEquals(expectedSeller, data.getSeller());
        ClassificationDTO classification1 = data.getClassification();
        assertEquals(1, classification1.getMinimumMonths().intValue());
        AddressDTO address1 = data.getAddress();
        assertTrue(address1.isStatus());
        assertEquals("Area", address1.getArea());
        assertEquals("Oxford", address1.getCity());
        assertEquals("42", address1.getFlatNumber());
        assertEquals(1, address1.getUser_id().intValue());
        assertSame(randomUUIDResult, address1.getUuid());
        assertTrue(classification1.getIsFeatured());
        assertEquals("Building", address1.getBuilding());
        assertEquals("Street Name", address1.getStreetName());
        assertEquals("S", classification1.getClassificationType());
        assertEquals(1, classification1.getMinimumDays().intValue());
        assertNull(address1.getId());
        verify(productRepository).findOne((org.springframework.data.jpa.domain.Specification<Product>) any());
    }

    @Test
    void testNewProduct() {
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

        Address address = new Address();
        address.setArea("Area");
        address.setBuilding("Building");
        address.setCity("Oxford");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        address.setCreatedAt(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        address.setFlatNumber("42");
        address.setId(1);
        address.setIsDeleted(true);
        address.setStatus(true);
        address.setStreetName("Street Name");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        address.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        address.setUser(user);
        UUID randomUUIDResult = UUID.randomUUID();
        address.setUuid(randomUUIDResult);

        User user1 = new User();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        user1.setEmail("jane.doe@example.org");
        user1.setFullName("Dr Jane Doe");
        user1.setId(1);
        user1.setIsDeleted(true);
        user1.setIsEnabled(true);
        user1.setIsseller(true);
        user1.setPassword("Test@123");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

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

        Category category = new Category();
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
        category.setCreatedBy(user1);
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
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user2);
        category.setUrl("https://example.org/example");

        Classification classification = new Classification();
        classification.setClassificationType(ClassificationType.SHORT);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        classification.setCreatedAt(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        classification.setId(1);
        classification.setIsDeleted(true);
        classification.setIsFeatured(true);
        classification.setMinimumDays(1);
        classification.setMinimumMonths(1);
        classification.setRentLongTerm(1L);
        classification.setRentPerDay(1L);
        classification.setRentPerMonth(1L);
        classification.setRentPerWeek(1L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        classification.setUpdatedAt(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        classification.setUuid(UUID.randomUUID());

        Currency currency = new Currency();
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        currency.setCreatedAt(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant());
        currency.setCurrencyIso("GBP");
        currency.setCurrencyName("GBP");
        currency.setCurrencySymbol("GBP");
        currency.setId(1);
        currency.setIsDeleted(true);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        currency.setUpdatedAt(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant());
        currency.setUuid(UUID.randomUUID());

        User user3 = new User();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant());
        user3.setEmail("jane.doe@example.org");
        user3.setFullName("Dr Jane Doe");
        user3.setId(1);
        user3.setIsDeleted(true);
        user3.setIsEnabled(true);
        user3.setIsseller(true);
        user3.setPassword("Test@123");
        user3.setPhone_number("4105551212");
        user3.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant());
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user4 = new User();
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreatedAt(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant());
        user4.setEmail("jane.doe@example.org");
        user4.setFullName("Dr Jane Doe");
        user4.setId(1);
        user4.setIsDeleted(true);
        user4.setIsEnabled(true);
        user4.setIsseller(true);
        user4.setPassword("Test@123");
        user4.setPhone_number("4105551212");
        user4.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setUpdatedAt(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant());
        user4.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user5 = new User();
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user5.setCreatedAt(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant());
        user5.setEmail("jane.doe@example.org");
        user5.setFullName("Dr Jane Doe");
        user5.setId(1);
        user5.setIsDeleted(true);
        user5.setIsEnabled(true);
        user5.setIsseller(true);
        user5.setPassword("Test@123");
        user5.setPhone_number("4105551212");
        user5.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user5.setUpdatedAt(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant());
        user5.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user6 = new User();
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user6.setCreatedAt(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant());
        user6.setEmail("jane.doe@example.org");
        user6.setFullName("Dr Jane Doe");
        user6.setId(1);
        user6.setIsDeleted(true);
        user6.setIsEnabled(true);
        user6.setIsseller(true);
        user6.setPassword("Test@123");
        user6.setPhone_number("4105551212");
        user6.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user6.setUpdatedAt(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant());
        user6.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        Category category1 = new Category();
        category1.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category1.setCreatedAt(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant());
        category1.setCreatedBy(user5);
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
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category1.setUpdatedAt(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant());
        category1.setUpdatedBy(user6);
        category1.setUrl("https://example.org/example");

        TBYB tbyb = new TBYB();
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        tbyb.setCreatedAt(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant());
        tbyb.setId(1);
        tbyb.setIsDeleted(true);
        tbyb.setTrialDays(TrialDaysEnum.FIVE);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        tbyb.setUpdatedAt(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant());
        tbyb.setUuid(UUID.randomUUID());

        Product product = new Product();
        product.setAddress(address);
        product.setBrand("Brand");
        product.setCartList(new ArrayList<>());
        product.setCategory(category);
        product.setClassification(classification);
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        product.setCreatedAt(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant());
        product.setCurrency(currency);
        product.setCustomer(user3);
        product.setDescription("The characteristics of someone or something");
        product.setFeatures("Features");
        product.setFinalPrice(1L);
        product.setId(1);
        product.setIsActive(true);
        product.setIsApproved(true);
        product.setIsDeleted(true);
        product.setIsFeatured(true);
        product.setSeller(user4);
        product.setSlug("Slug");
        product.setSubCategory(category1);
        product.setTags("Tags");
        product.setTbyb(tbyb);
        product.setTitle("Dr");
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        product.setUpdatedAt(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant());
        UUID randomUUIDResult1 = UUID.randomUUID();
        product.setUuid(randomUUIDResult1);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.save((Product) any())).thenReturn(product);

        TBYB tbyb1 = new TBYB();
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        tbyb1.setCreatedAt(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant());
        tbyb1.setId(1);
        tbyb1.setIsDeleted(true);
        tbyb1.setTrialDays(TrialDaysEnum.FIVE);
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        tbyb1.setUpdatedAt(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant());
        tbyb1.setUuid(UUID.randomUUID());
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        when(tbybRepository.save((TBYB) any())).thenReturn(tbyb1);
        SessionDelegatorBaseImpl sessionDelegatorBaseImpl = mock(SessionDelegatorBaseImpl.class);
        doNothing().when(sessionDelegatorBaseImpl).refresh((Object) any());
        SessionDelegatorBaseImpl entityManager = new SessionDelegatorBaseImpl(sessionDelegatorBaseImpl);
        CartDaoImpl cartDao = new CartDaoImpl(mock(CartRepository.class));
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        ProductDaoImpl productDao = new ProductDaoImpl(productRepository, cartDao, classificationRepository, tbybRepository,
                entityManager, userDao, new RedisTemplate());

        User user7 = new User();
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user7.setCreatedAt(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant());
        user7.setEmail("jane.doe@example.org");
        user7.setFullName("Dr Jane Doe");
        user7.setId(1);
        user7.setIsDeleted(true);
        user7.setIsEnabled(true);
        user7.setIsseller(true);
        user7.setPassword("Test@123");
        user7.setPhone_number("4105551212");
        user7.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user7.setUpdatedAt(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant());
        user7.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Integer) any())).thenReturn(Optional.of(user7));
        ProductController productController = new ProductController(mock(ProductRepository.class),
                new ProductServiceImpl(productDao, new UserDaoImpl(userRepository)));
        TBYBDTO tbybdto = mock(TBYBDTO.class);
        when(tbybdto.getTrialDays()).thenReturn("Trial Days");

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setAddressId(123);
        createProductRequest.setBrand("Brand");
        createProductRequest.setCategoryId(123);
        createProductRequest.setClassification(mock(ClassificationDTO.class));
        createProductRequest.setCurrencyId(123);
        createProductRequest.setDescription("The characteristics of someone or something");
        createProductRequest.setFeatures("Features");
        createProductRequest.setFinalPrice(1L);
        createProductRequest.setOfferingType(OfferingType.TBYB);
        createProductRequest.setSubcategoryId(123);
        createProductRequest.setTags("Tags");
        createProductRequest.setTbyb(tbybdto);
        createProductRequest.setTitle("Dr");
        ResponseDTO<ProductResponse> actualNewProductResult = productController.newProduct(123, createProductRequest);
        assertEquals("2000", actualNewProductResult.getCode());
        assertEquals("Operation completed successfully", actualNewProductResult.getMessage());
        assertEquals(HttpStatus.OK, actualNewProductResult.getHttpStatus());
        ProductResponse data = actualNewProductResult.getData();
        assertSame(randomUUIDResult1, data.getUuid());
        assertEquals("Brand", data.getBrand());
        assertEquals("The characteristics of someone or something", data.getDescription());
        assertEquals("Slug", data.getSlug());
        assertEquals("Features", data.getFeatures());
        assertEquals("Tags", data.getTags());
        assertEquals(1L, data.getFinalPrice().longValue());
        assertEquals("Dr", data.getTitle());
        Instant expectedUpdatedAt = data.getCreatedAt();
        assertSame(expectedUpdatedAt, data.getUpdatedAt());
        assertEquals(1, data.getId().intValue());
        CategoryDTO expectedCategory = data.getSubCategory();
        assertEquals(expectedCategory, data.getCategory());
        assertTrue(data.getIsActive());
        assertTrue(data.getIsApproved());
        assertTrue(data.getIsFeatured());
        UserResponseDTO expectedSeller = data.getCustomer();
        assertEquals(expectedSeller, data.getSeller());
        ClassificationDTO classification1 = data.getClassification();
        assertEquals(1, classification1.getMinimumMonths().intValue());
        AddressDTO address1 = data.getAddress();
        assertTrue(address1.isStatus());
        assertEquals("Area", address1.getArea());
        assertEquals("Oxford", address1.getCity());
        assertEquals("42", address1.getFlatNumber());
        assertEquals(1, address1.getUser_id().intValue());
        assertSame(randomUUIDResult, address1.getUuid());
        assertTrue(classification1.getIsFeatured());
        assertEquals("Building", address1.getBuilding());
        assertEquals("Street Name", address1.getStreetName());
        assertEquals("S", classification1.getClassificationType());
        assertEquals(1, classification1.getMinimumDays().intValue());
        assertNull(address1.getId());
        verify(productRepository).save((Product) any());
        verify(tbybRepository).save((TBYB) any());
        verify(sessionDelegatorBaseImpl).refresh((Object) any());
        verify(userRepository).findById((Integer) any());
        verify(tbybdto).getTrialDays();
    }

    @Test
    void testGetProducts() throws Exception {
        when(this.productService.getProducts((OfferingType) any(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/product/products");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("q", String.valueOf(OfferingType.TBYB));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testGetFeaturedProducts() throws Exception {
        when(this.productService.getFeaturedProducts((OfferingType) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/product/products/featured");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("type", String.valueOf(OfferingType.TBYB));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testGetUnapprovedProducts() throws Exception {
        when(this.productService.getUnapprovedProducts((com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/product/products/unapproved");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testGetUnapprovedProducts2() throws Exception {
        when(this.productService.getUnapprovedProducts((com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/api/product/products/unapproved")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testDeleteProductById() throws Exception {
        doNothing().when(this.productService).deleteProductById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v1/api/product/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><data>Product Deleted successfully</data></ResponseDTO>"));
    }

    @Test
    void testDeleteProductById2() throws Exception {
        doNothing().when(this.productService).deleteProductById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/v1/api/product/delete/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><data>Product Deleted successfully</data></ResponseDTO>"));
    }

    @Test
    void testAddProductToCart() throws Exception {
        doNothing().when(this.productService).addProductToCart((Integer) any(), (Integer) any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/v1/api/product/addProductToCart");
        MockHttpServletRequestBuilder paramResult = postResult.param("productId", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus></ResponseDTO>"));
    }

    @Test
    void testGetProductFromCart() throws Exception {
        when(this.productService.getProductFromCart((Integer) any(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/product/getProductFromCart");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("userId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testGetProductFromCart2() throws Exception {
        when(this.productService.getProductFromCart((Integer) any(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/api/product/getProductFromCart")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("userId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testDeleteProductFromCart() throws Exception {
        doNothing().when(this.productService).deleteProductFromCart((Integer) any(), (Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/v1/api/product/deleteProductToCart");
        MockHttpServletRequestBuilder paramResult = deleteResult.param("productId", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus></ResponseDTO>"));
    }

    @Test
    void testGetRecentlyAddedProducts() throws Exception {
        when(this.productService.getRecentlyAddedProducts((Integer) any())).thenReturn(new HashSet<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/product/recentlyAddedProducts");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus></ResponseDTO>"));
    }

    @Test
    void testRecentlyAddedProducts() throws Exception {
        doNothing().when(this.productService).recentlyAddedProducts((Integer) any(), (Integer) any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/v1/api/product/recentlyAddedProducts");
        MockHttpServletRequestBuilder paramResult = postResult.param("productId", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus></ResponseDTO>"));
    }

    @Test
    void testGetProductBySubCategory() throws Exception {
        when(this.productService.getFromSubCategory((Integer) any(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/product/sub-category");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("subcategory_id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testGetProductBySubCategory2() throws Exception {
        when(this.productService.getFromSubCategory((Integer) any(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/api/product/sub-category")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("subcategory_id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testGetProductsByCategory() throws Exception {
        when(this.productService.getProductsBy((String) any(), (com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/api/product/products/by")
                .param("category", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }

    @Test
    void testGetProductsByCategory2() throws Exception {
        when(this.productService.getProductsBy((String) any(), (com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/api/product/products/by")
                .param("category", "foo")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><pageSize>0</pageSize><totalPages>1</totalPages><pageNumber>1</pageNumber><totalRecords>0"
                                + "</totalRecords></ResponseDTO>"));
    }
}

