package com.strabl.service.product.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.AddressDTO;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ClassificationDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.TBYBDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.dao.UserDao;
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

import java.time.Instant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductDao productDao;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private UserDao userDao;

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
        UUID randomUUIDResult1 = UUID.randomUUID();
        classification.setUuid(randomUUIDResult1);

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
        UUID randomUUIDResult2 = UUID.randomUUID();
        product.setUuid(randomUUIDResult2);
        when(this.productDao.findBySlug((String) any())).thenReturn(product);
        ProductResponse actualProductBySlug = this.productServiceImpl.getProductBySlug("Slug");
        assertSame(randomUUIDResult2, actualProductBySlug.getUuid());
        assertEquals("Brand", actualProductBySlug.getBrand());
        assertEquals("The characteristics of someone or something", actualProductBySlug.getDescription());
        assertEquals("Slug", actualProductBySlug.getSlug());
        assertEquals("Features", actualProductBySlug.getFeatures());
        assertEquals("Tags", actualProductBySlug.getTags());
        assertEquals(1L, actualProductBySlug.getFinalPrice().longValue());
        assertEquals("Dr", actualProductBySlug.getTitle());
        Instant expectedUpdatedAt = actualProductBySlug.getCreatedAt();
        assertSame(expectedUpdatedAt, actualProductBySlug.getUpdatedAt());
        assertEquals(1, actualProductBySlug.getId().intValue());
        CategoryDTO subCategory = actualProductBySlug.getSubCategory();
        assertEquals(subCategory, actualProductBySlug.getCategory());
        assertTrue(actualProductBySlug.getIsActive());
        assertTrue(actualProductBySlug.getIsApproved());
        assertTrue(actualProductBySlug.getIsFeatured());
        UserResponseDTO expectedSeller = actualProductBySlug.getCustomer();
        assertEquals(expectedSeller, actualProductBySlug.getSeller());
        AddressDTO address1 = actualProductBySlug.getAddress();
        assertNull(address1.getId());
        assertEquals("42", address1.getFlatNumber());
        assertEquals("Oxford", address1.getCity());
        assertEquals("Building", address1.getBuilding());
        assertEquals("Area", address1.getArea());
        assertEquals("https://example.org/example", subCategory.getCategoryUrl());
        assertEquals(1, subCategory.getCreatedBy().intValue());
        ClassificationDTO classification1 = actualProductBySlug.getClassification();
        assertEquals(1L, classification1.getRentPerWeek().longValue());
        assertSame(randomUUIDResult1, classification1.getUuid());
        assertTrue(address1.isStatus());
        assertSame(randomUUIDResult, address1.getUuid());
        assertEquals("S", classification1.getClassificationType());
        assertTrue(classification1.getIsFeatured());
        assertEquals(1L, classification1.getRentLongTerm().longValue());
        assertEquals(1L, classification1.getRentPerDay().longValue());
        assertEquals(1L, classification1.getRentPerMonth().longValue());
        assertEquals(1, address1.getUser_id().intValue());
        assertEquals(1, classification1.getMinimumMonths().intValue());
        assertEquals("Dr Jane Doe", subCategory.getCreatedByName());
        assertEquals("Street Name", address1.getStreetName());
        assertEquals(1, classification1.getMinimumDays().intValue());
        verify(this.productDao).findBySlug((String) any());
    }

    @Test
    void testAddProduct() {
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
        when(this.userDao.findByIdOrThrow((Integer) any())).thenReturn(user);
        when(this.productDao.create((Product) any())).thenThrow(StrablException.of(ResponseType.SUCCESS));
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
        assertThrows(StrablException.class, () -> this.productServiceImpl.addProduct(createProductRequest, 123));
        verify(this.userDao).findByIdOrThrow((Integer) any());
        verify(this.productDao).create((Product) any());
        verify(tbybdto).getTrialDays();
    }

    @Test
    void testAddProduct2() {
        User user = mock(User.class);
        when(user.getIsseller()).thenThrow(StrablException.of(ResponseType.SUCCESS));
        when(user.getIsEnabled()).thenReturn(true);
        doNothing().when(user).setCreatedAt((Instant) any());
        doNothing().when(user).setId((Integer) any());
        doNothing().when(user).setIsDeleted((Boolean) any());
        doNothing().when(user).setUpdatedAt((Instant) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFullName((String) any());
        doNothing().when(user).setIsEnabled((Boolean) any());
        doNothing().when(user).setIsseller((Boolean) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhone_number((String) any());
        doNothing().when(user).setStatus((UserStatus) any());
        doNothing().when(user).setUserUuid((String) any());
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
        when(this.userDao.findByIdOrThrow((Integer) any())).thenReturn(user);
        when(this.productDao.create((com.strabl.sdk.domain.entity.Product) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
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
        assertThrows(StrablException.class, () -> this.productServiceImpl.addProduct(createProductRequest, 123));
        verify(this.userDao).findByIdOrThrow((Integer) any());
        verify(user).getIsEnabled();
        verify(user).getIsseller();
        verify(user).setCreatedAt((Instant) any());
        verify(user).setId((Integer) any());
        verify(user).setIsDeleted((Boolean) any());
        verify(user).setUpdatedAt((Instant) any());
        verify(user).setEmail((String) any());
        verify(user).setFullName((String) any());
        verify(user).setIsEnabled((Boolean) any());
        verify(user).setIsseller((Boolean) any());
        verify(user).setPassword((String) any());
        verify(user).setPhone_number((String) any());
        verify(user).setStatus((UserStatus) any());
        verify(user).setUserUuid((String) any());
    }

    @Test
    void testGetProducts() {

        when(this.productDao.searchByOfferingType((OfferingType) any(), (PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.productServiceImpl.getProducts(OfferingType.TBYB, new PagedResponseRequest()).toList().isEmpty());
        verify(this.productDao).searchByOfferingType((OfferingType) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetProducts2() {

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
        address.setUuid(UUID.randomUUID());

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
        product.setUuid(UUID.randomUUID());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        PageImpl<Product> pageImpl = new PageImpl<>(productList);
        when(this.productDao.searchByOfferingType((OfferingType) any(), (PagedResponseRequest) any())).thenReturn(pageImpl);
        assertEquals(1, this.productServiceImpl.getProducts(OfferingType.TBYB, new PagedResponseRequest()).toList().size());
        verify(this.productDao).searchByOfferingType((OfferingType) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetProducts3() {

        when(this.productDao.searchByOfferingType((OfferingType) any(), (PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.productServiceImpl.getProducts(OfferingType.RENTAL, new PagedResponseRequest()).toList().isEmpty());
        verify(this.productDao).searchByOfferingType((OfferingType) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetProducts4() {

        when(this.productDao.searchByOfferingType((OfferingType) any(), (PagedResponseRequest) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class,
                () -> this.productServiceImpl.getProducts(OfferingType.TBYB, new PagedResponseRequest()));
        verify(this.productDao).searchByOfferingType((OfferingType) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetProductsBy() {

        when(this.productDao.getByCategory((String) any(), (PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.productServiceImpl.getProductsBy("Category", new PagedResponseRequest()).toList().isEmpty());
        verify(this.productDao).getByCategory((String) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetProductsBy2() {

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
        address.setUuid(UUID.randomUUID());

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
        product.setUuid(UUID.randomUUID());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        PageImpl<Product> pageImpl = new PageImpl<>(productList);
        when(this.productDao.getByCategory((String) any(), (PagedResponseRequest) any())).thenReturn(pageImpl);
        assertEquals(1, this.productServiceImpl.getProductsBy("Category", new PagedResponseRequest()).toList().size());
        verify(this.productDao).getByCategory((String) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetProductsBy3() {

        when(this.productDao.getByCategory((String) any(), (PagedResponseRequest) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class,
                () -> this.productServiceImpl.getProductsBy("Category", new PagedResponseRequest()));
        verify(this.productDao).getByCategory((String) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetFeaturedProducts() {

        when(this.productDao.getFeaturedRandomly((OfferingType) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.productServiceImpl.getFeaturedProducts(OfferingType.TBYB).toList().isEmpty());
        verify(this.productDao).getFeaturedRandomly((OfferingType) any(), (Integer) any());
    }

    @Test
    void testGetFeaturedProducts2() {

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
        address.setUuid(UUID.randomUUID());

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
        product.setUuid(UUID.randomUUID());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        PageImpl<Product> pageImpl = new PageImpl<>(productList);
        when(this.productDao.getFeaturedRandomly((OfferingType) any(), (Integer) any())).thenReturn(pageImpl);
        assertEquals(1, this.productServiceImpl.getFeaturedProducts(OfferingType.TBYB).toList().size());
        verify(this.productDao).getFeaturedRandomly((OfferingType) any(), (Integer) any());
    }

    @Test
    void testGetFeaturedProducts3() {

        when(this.productDao.getFeaturedRandomly((OfferingType) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.productServiceImpl.getFeaturedProducts(OfferingType.RENTAL).toList().isEmpty());
        verify(this.productDao).getFeaturedRandomly((OfferingType) any(), (Integer) any());
    }

    @Test
    void testGetFeaturedProducts4() {

        when(this.productDao.getFeaturedRandomly((OfferingType) any(), (Integer) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class, () -> this.productServiceImpl.getFeaturedProducts(OfferingType.TBYB));
        verify(this.productDao).getFeaturedRandomly((OfferingType) any(), (Integer) any());
    }

    @Test
    void testGetUnapprovedProducts() {

        when(this.productDao.getUnapprovedProducts((PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.productServiceImpl.getUnapprovedProducts(new PagedResponseRequest()).toList().isEmpty());
        verify(this.productDao).getUnapprovedProducts((PagedResponseRequest) any());
    }

    @Test
    void testGetUnapprovedProducts2() {

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
        address.setUuid(UUID.randomUUID());

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
        product.setUuid(UUID.randomUUID());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        PageImpl<Product> pageImpl = new PageImpl<>(productList);
        when(this.productDao.getUnapprovedProducts((PagedResponseRequest) any())).thenReturn(pageImpl);
        assertEquals(1, this.productServiceImpl.getUnapprovedProducts(new PagedResponseRequest()).toList().size());
        verify(this.productDao).getUnapprovedProducts((PagedResponseRequest) any());
    }

    @Test
    void testGetUnapprovedProducts3() {

        when(this.productDao.getUnapprovedProducts((PagedResponseRequest) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class,
                () -> this.productServiceImpl.getUnapprovedProducts(new PagedResponseRequest()));
        verify(this.productDao).getUnapprovedProducts((PagedResponseRequest) any());
    }

    @Test
    void testUpdateApprovalStatus() {

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
        UUID randomUUIDResult1 = UUID.randomUUID();
        classification.setUuid(randomUUIDResult1);

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
        UUID randomUUIDResult2 = UUID.randomUUID();
        product.setUuid(randomUUIDResult2);
        when(this.productDao.updateApprovalStatus((Integer) any(), anyBoolean())).thenReturn(product);
        ProductResponse actualUpdateApprovalStatusResult = this.productServiceImpl.updateApprovalStatus(123, true);
        assertSame(randomUUIDResult2, actualUpdateApprovalStatusResult.getUuid());
        assertEquals("Brand", actualUpdateApprovalStatusResult.getBrand());
        assertEquals("The characteristics of someone or something", actualUpdateApprovalStatusResult.getDescription());
        assertEquals("Slug", actualUpdateApprovalStatusResult.getSlug());
        assertEquals("Features", actualUpdateApprovalStatusResult.getFeatures());
        assertEquals("Tags", actualUpdateApprovalStatusResult.getTags());
        assertEquals(1L, actualUpdateApprovalStatusResult.getFinalPrice().longValue());
        assertEquals("Dr", actualUpdateApprovalStatusResult.getTitle());
        Instant expectedUpdatedAt = actualUpdateApprovalStatusResult.getCreatedAt();
        assertSame(expectedUpdatedAt, actualUpdateApprovalStatusResult.getUpdatedAt());
        assertEquals(1, actualUpdateApprovalStatusResult.getId().intValue());
        CategoryDTO subCategory = actualUpdateApprovalStatusResult.getSubCategory();
        assertEquals(subCategory, actualUpdateApprovalStatusResult.getCategory());
        assertTrue(actualUpdateApprovalStatusResult.getIsActive());
        assertTrue(actualUpdateApprovalStatusResult.getIsApproved());
        assertTrue(actualUpdateApprovalStatusResult.getIsFeatured());
        UserResponseDTO expectedSeller = actualUpdateApprovalStatusResult.getCustomer();
        assertEquals(expectedSeller, actualUpdateApprovalStatusResult.getSeller());
        AddressDTO address1 = actualUpdateApprovalStatusResult.getAddress();
        assertNull(address1.getId());
        assertEquals("42", address1.getFlatNumber());
        assertEquals("Oxford", address1.getCity());
        assertEquals("Building", address1.getBuilding());
        assertEquals("Area", address1.getArea());
        assertEquals("https://example.org/example", subCategory.getCategoryUrl());
        assertEquals(1, subCategory.getCreatedBy().intValue());
        ClassificationDTO classification1 = actualUpdateApprovalStatusResult.getClassification();
        assertEquals(1L, classification1.getRentPerWeek().longValue());
        assertSame(randomUUIDResult1, classification1.getUuid());
        assertTrue(address1.isStatus());
        assertSame(randomUUIDResult, address1.getUuid());
        assertEquals("S", classification1.getClassificationType());
        assertTrue(classification1.getIsFeatured());
        assertEquals(1L, classification1.getRentLongTerm().longValue());
        assertEquals(1L, classification1.getRentPerDay().longValue());
        assertEquals(1L, classification1.getRentPerMonth().longValue());
        assertEquals(1, address1.getUser_id().intValue());
        assertEquals(1, classification1.getMinimumMonths().intValue());
        assertEquals("Dr Jane Doe", subCategory.getCreatedByName());
        assertEquals("Street Name", address1.getStreetName());
        assertEquals(1, classification1.getMinimumDays().intValue());
        verify(this.productDao).updateApprovalStatus((Integer) any(), anyBoolean());
    }

    @Test
    void testDeleteProductById() {

        doNothing().when(this.productDao).deleteProductById((Integer) any());
        this.productServiceImpl.deleteProductById(1);
        verify(this.productDao).deleteProductById((Integer) any());
    }

    @Test
    void testDeleteProductById2() {

        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.productDao).deleteProductById((Integer) any());
        assertThrows(StrablException.class, () -> this.productServiceImpl.deleteProductById(1));
        verify(this.productDao).deleteProductById((Integer) any());
    }

    @Test
    void testAddProductToCart() {

        doNothing().when(this.productDao).addProductToCart((Integer) any(), (Integer) any());
        this.productServiceImpl.addProductToCart(123, 123);
        verify(this.productDao).addProductToCart((Integer) any(), (Integer) any());
    }

    @Test
    void testAddProductToCart2() {

        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.productDao)
                .addProductToCart((Integer) any(), (Integer) any());
        assertThrows(StrablException.class, () -> this.productServiceImpl.addProductToCart(123, 123));
        verify(this.productDao).addProductToCart((Integer) any(), (Integer) any());
    }

    @Test
    void testGetProductFromCart() {

        PageImpl<ProductResponse> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.productDao.getProductFromCart((Integer) any(), (PagedResponseRequest) any())).thenReturn(pageImpl);
        Page<ProductResponse> actualProductFromCart = this.productServiceImpl.getProductFromCart(123,
                new PagedResponseRequest());
        assertSame(pageImpl, actualProductFromCart);
        assertTrue(actualProductFromCart.toList().isEmpty());
        verify(this.productDao).getProductFromCart((Integer) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetProductFromCart2() {

        when(this.productDao.getProductFromCart((Integer) any(), (PagedResponseRequest) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class,
                () -> this.productServiceImpl.getProductFromCart(123, new PagedResponseRequest()));
        verify(this.productDao).getProductFromCart((Integer) any(), (PagedResponseRequest) any());
    }

    @Test
    void testDeleteProductFromCart() {

        doNothing().when(this.productDao).deleteProductFromCart((Integer) any(), (Integer) any());
        this.productServiceImpl.deleteProductFromCart(123, 123);
        verify(this.productDao).deleteProductFromCart((Integer) any(), (Integer) any());
    }

    @Test
    void testDeleteProductFromCart2() {

        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.productDao)
                .deleteProductFromCart((Integer) any(), (Integer) any());
        assertThrows(StrablException.class, () -> this.productServiceImpl.deleteProductFromCart(123, 123));
        verify(this.productDao).deleteProductFromCart((Integer) any(), (Integer) any());
    }

    @Test
    void testGetRecentlyAddedProducts() {

        HashSet<ProductResponse> productResponseSet = new HashSet<>();
        when(this.productDao.getRecentlyAddedProducts((Integer) any())).thenReturn(productResponseSet);
        Set<ProductResponse> actualRecentlyAddedProducts = this.productServiceImpl.getRecentlyAddedProducts(123);
        assertSame(productResponseSet, actualRecentlyAddedProducts);
        assertTrue(actualRecentlyAddedProducts.isEmpty());
        verify(this.productDao).getRecentlyAddedProducts((Integer) any());
    }

    @Test
    void testGetRecentlyAddedProducts2() {

        when(this.productDao.getRecentlyAddedProducts((Integer) any())).thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class, () -> this.productServiceImpl.getRecentlyAddedProducts(123));
        verify(this.productDao).getRecentlyAddedProducts((Integer) any());
    }

    @Test
    void testRecentlyAddedProducts() {

        doNothing().when(this.productDao).recentlyAddedProducts((Integer) any(), (Integer) any());
        this.productServiceImpl.recentlyAddedProducts(123, 123);
        verify(this.productDao).recentlyAddedProducts((Integer) any(), (Integer) any());
    }

    @Test
    void testRecentlyAddedProducts2() {

        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.productDao)
                .recentlyAddedProducts((Integer) any(), (Integer) any());
        assertThrows(StrablException.class, () -> this.productServiceImpl.recentlyAddedProducts(123, 123));
        verify(this.productDao).recentlyAddedProducts((Integer) any(), (Integer) any());
    }

    @Test
    void testGetFromSubCategory() {

        when(this.productDao.getFromSubCategory((Integer) any(), (PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.productServiceImpl.getFromSubCategory(1, new PagedResponseRequest()).toList().isEmpty());
        verify(this.productDao).getFromSubCategory((Integer) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetFromSubCategory2() {

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
        address.setUuid(UUID.randomUUID());

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
        product.setUuid(UUID.randomUUID());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        PageImpl<Product> pageImpl = new PageImpl<>(productList);
        when(this.productDao.getFromSubCategory((Integer) any(), (PagedResponseRequest) any())).thenReturn(pageImpl);
        assertEquals(1, this.productServiceImpl.getFromSubCategory(1, new PagedResponseRequest()).toList().size());
        verify(this.productDao).getFromSubCategory((Integer) any(), (PagedResponseRequest) any());
    }

    @Test
    void testGetFromSubCategory3() {

        when(this.productDao.getFromSubCategory((Integer) any(), (PagedResponseRequest) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class,
                () -> this.productServiceImpl.getFromSubCategory(1, new PagedResponseRequest()));
        verify(this.productDao).getFromSubCategory((Integer) any(), (PagedResponseRequest) any());
    }
}

