package com.strabl.service.product.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.domain.dao.impl.CategoryDaoImpl;
import com.strabl.sdk.domain.dao.impl.ProductDaoImpl;
import com.strabl.sdk.domain.dao.impl.UserDaoImpl;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import com.strabl.sdk.domain.repository.CategoryRepository;
import com.strabl.sdk.domain.repository.ClassificationRepository;
import com.strabl.sdk.domain.repository.ProductRepository;
import com.strabl.sdk.domain.repository.TBYBRepository;
import com.strabl.sdk.domain.repository.UserRepository;
import com.strabl.service.product.service.CategoryService;
import com.strabl.service.product.service.impl.CategoryServiceImpl;

import java.time.Instant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Optional;

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

@ContextConfiguration(classes = {CategoryController.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    @Test
    void testAddBuyCategory() throws Exception {
        when(this.categoryService.getByFilterCategory(anyBoolean(), anyBoolean(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/v1/api/category/categories/addBuyCategory");
        MockHttpServletRequestBuilder paramResult = postResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.categoryController)
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
    void testAddCategory() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of CategoryController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("iloveyou");
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
        user1.setPassword("iloveyou");
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
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.save((Category) any())).thenReturn(category);

        User user2 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant());
        user2.setEmail("jane.doe@example.org");
        user2.setFullName("Dr Jane Doe");
        user2.setId(1);
        user2.setIsDeleted(true);
        user2.setIsEnabled(true);
        user2.setIsseller(true);
        user2.setPassword("iloveyou");
        user2.setPhone_number("4105551212");
        user2.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant());
        user2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Integer) any())).thenReturn(Optional.of(user2));
        UserDaoImpl userDao = new UserDaoImpl(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        CategoryController categoryController = new CategoryController(
                new CategoryServiceImpl(new CategoryDaoImpl(categoryRepository, new ProductDaoImpl(productRepository, null,
                        classificationRepository, tbybRepository, null, null, new RedisTemplate()), userDao)));
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
        ResponseDTO<CategoryDTO> actualAddCategoryResult = categoryController.addCategory(categoryDTO, 123);
        assertEquals("2000", actualAddCategoryResult.getCode());
        assertEquals("Operation completed successfully", actualAddCategoryResult.getMessage());
        assertEquals(HttpStatus.OK, actualAddCategoryResult.getHttpStatus());
        CategoryDTO data = actualAddCategoryResult.getData();
        assertEquals("https://example.org/example", data.getCategoryUrl());
        assertEquals("https://example.org/example", data.getUrl());
        assertEquals("Dr Jane Doe", data.getUpdatedByName());
        assertEquals(1, data.getUpdatedBy().intValue());
        assertEquals(1, data.getOrderIndex().intValue());
        assertEquals("Name", data.getName());
        assertTrue(data.getIsTbyb());
        assertTrue(data.getIsRental());
        assertTrue(data.getIsRandomStuff());
        assertTrue(data.getIsPublished());
        assertTrue(data.getIsHome());
        assertEquals("Image", data.getImage());
        assertEquals("Icon", data.getIcon());
        assertEquals("The characteristics of someone or something", data.getDescription());
        assertEquals("Dr Jane Doe", data.getCreatedByName());
        assertEquals(1, data.getCreatedBy().intValue());
        verify(categoryRepository).save((Category) any());
        verify(userRepository).findById((Integer) any());
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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of CategoryController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("iloveyou");
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
        user1.setPassword("iloveyou");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user2 = new User();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        user2.setEmail("jane.doe@example.org");
        user2.setFullName("Dr Jane Doe");
        user2.setId(1);
        user2.setIsDeleted(true);
        user2.setIsEnabled(true);
        user2.setIsseller(true);
        user2.setPassword("iloveyou");
        user2.setPhone_number("4105551212");
        user2.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        user2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user3 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant());
        user3.setEmail("jane.doe@example.org");
        user3.setFullName("Dr Jane Doe");
        user3.setId(1);
        user3.setIsDeleted(true);
        user3.setIsEnabled(true);
        user3.setIsseller(true);
        user3.setPassword("iloveyou");
        user3.setPhone_number("4105551212");
        user3.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant());
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Category category = mock(Category.class);
        when(category.getUpdatedBy()).thenReturn(user3);
        when(category.getCreatedBy()).thenReturn(user2);
        when(category.getIsHome()).thenReturn(true);
        when(category.getIsPublished()).thenReturn(true);
        when(category.getIsRandomStuff()).thenReturn(true);
        when(category.getIsRental()).thenReturn(true);
        when(category.getIsTbyb()).thenReturn(true);
        when(category.getOrderIndex()).thenReturn(1);
        when(category.getCategoryUrl()).thenReturn("https://example.org/example");
        when(category.getDescription()).thenReturn("The characteristics of someone or something");
        when(category.getIcon()).thenReturn("Icon");
        when(category.getImage()).thenReturn("Image");
        when(category.getName()).thenReturn("Name");
        when(category.getUrl()).thenReturn("https://example.org/example");
        doNothing().when(category).setCreatedAt((Instant) any());
        doNothing().when(category).setId((Integer) any());
        doNothing().when(category).setIsDeleted((Boolean) any());
        doNothing().when(category).setUpdatedAt((Instant) any());
        doNothing().when(category).setCategoryUrl((String) any());
        doNothing().when(category).setCreatedBy((User) any());
        doNothing().when(category).setDescription((String) any());
        doNothing().when(category).setIcon((String) any());
        doNothing().when(category).setImage((String) any());
        doNothing().when(category).setIsHome((Boolean) any());
        doNothing().when(category).setIsPublished((Boolean) any());
        doNothing().when(category).setIsRandomStuff((Boolean) any());
        doNothing().when(category).setIsRental((Boolean) any());
        doNothing().when(category).setIsTbyb((Boolean) any());
        doNothing().when(category).setName((String) any());
        doNothing().when(category).setOrderIndex((Integer) any());
        doNothing().when(category).setUpdatedBy((User) any());
        doNothing().when(category).setUrl((String) any());
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
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
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.save((Category) any())).thenReturn(category);

        User user4 = new User();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreatedAt(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        user4.setEmail("jane.doe@example.org");
        user4.setFullName("Dr Jane Doe");
        user4.setId(1);
        user4.setIsDeleted(true);
        user4.setIsEnabled(true);
        user4.setIsseller(true);
        user4.setPassword("iloveyou");
        user4.setPhone_number("4105551212");
        user4.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setUpdatedAt(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        user4.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Integer) any())).thenReturn(Optional.of(user4));
        UserDaoImpl userDao = new UserDaoImpl(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        CategoryController categoryController = new CategoryController(
                new CategoryServiceImpl(new CategoryDaoImpl(categoryRepository, new ProductDaoImpl(productRepository, null,
                        classificationRepository, tbybRepository, null, null, new RedisTemplate()), userDao)));
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
        ResponseDTO<CategoryDTO> actualAddCategoryResult = categoryController.addCategory(categoryDTO, 123);
        assertEquals("2000", actualAddCategoryResult.getCode());
        assertEquals("Operation completed successfully", actualAddCategoryResult.getMessage());
        assertEquals(HttpStatus.OK, actualAddCategoryResult.getHttpStatus());
        CategoryDTO data = actualAddCategoryResult.getData();
        assertEquals("https://example.org/example", data.getCategoryUrl());
        assertEquals("https://example.org/example", data.getUrl());
        assertEquals("Dr Jane Doe", data.getUpdatedByName());
        assertEquals(1, data.getUpdatedBy().intValue());
        assertEquals(1, data.getOrderIndex().intValue());
        assertEquals("Name", data.getName());
        assertTrue(data.getIsTbyb());
        assertTrue(data.getIsRental());
        assertTrue(data.getIsRandomStuff());
        assertTrue(data.getIsPublished());
        assertTrue(data.getIsHome());
        assertEquals("Image", data.getImage());
        assertEquals("Icon", data.getIcon());
        assertEquals("The characteristics of someone or something", data.getDescription());
        assertEquals("Dr Jane Doe", data.getCreatedByName());
        assertEquals(1, data.getCreatedBy().intValue());
        verify(categoryRepository).save((Category) any());
        verify(category, atLeast(1)).getCreatedBy();
        verify(category, atLeast(1)).getUpdatedBy();
        verify(category).getIsHome();
        verify(category).getIsPublished();
        verify(category).getIsRandomStuff();
        verify(category).getIsRental();
        verify(category).getIsTbyb();
        verify(category).getOrderIndex();
        verify(category).getCategoryUrl();
        verify(category).getDescription();
        verify(category).getIcon();
        verify(category).getImage();
        verify(category).getName();
        verify(category).getUrl();
        verify(category).setCreatedAt((Instant) any());
        verify(category).setId((Integer) any());
        verify(category).setIsDeleted((Boolean) any());
        verify(category).setUpdatedAt((Instant) any());
        verify(category).setCategoryUrl((String) any());
        verify(category).setCreatedBy((User) any());
        verify(category).setDescription((String) any());
        verify(category).setIcon((String) any());
        verify(category).setImage((String) any());
        verify(category).setIsHome((Boolean) any());
        verify(category).setIsPublished((Boolean) any());
        verify(category).setIsRandomStuff((Boolean) any());
        verify(category).setIsRental((Boolean) any());
        verify(category).setIsTbyb((Boolean) any());
        verify(category).setName((String) any());
        verify(category).setOrderIndex((Integer) any());
        verify(category).setUpdatedBy((User) any());
        verify(category).setUrl((String) any());
        verify(userRepository).findById((Integer) any());
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
    void testAddCategory3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of CategoryController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("iloveyou");
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
        user1.setPassword("iloveyou");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        User user2 = new User();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        user2.setEmail("jane.doe@example.org");
        user2.setFullName("Dr Jane Doe");
        user2.setId(1);
        user2.setIsDeleted(true);
        user2.setIsEnabled(true);
        user2.setIsseller(true);
        user2.setPassword("iloveyou");
        user2.setPhone_number("4105551212");
        user2.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        user2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        User user3 = mock(User.class);
        when(user3.getId()).thenReturn(1);
        when(user3.getFullName()).thenReturn("Dr Jane Doe");
        doNothing().when(user3).setCreatedAt((Instant) any());
        doNothing().when(user3).setId((Integer) any());
        doNothing().when(user3).setIsDeleted((Boolean) any());
        doNothing().when(user3).setUpdatedAt((Instant) any());
        doNothing().when(user3).setEmail((String) any());
        doNothing().when(user3).setFullName((String) any());
        doNothing().when(user3).setIsEnabled((Boolean) any());
        doNothing().when(user3).setIsseller((Boolean) any());
        doNothing().when(user3).setPassword((String) any());
        doNothing().when(user3).setPhone_number((String) any());
        doNothing().when(user3).setStatus((UserStatus) any());
        doNothing().when(user3).setUserUuid((String) any());
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant());
        user3.setEmail("jane.doe@example.org");
        user3.setFullName("Dr Jane Doe");
        user3.setId(1);
        user3.setIsDeleted(true);
        user3.setIsEnabled(true);
        user3.setIsseller(true);
        user3.setPassword("iloveyou");
        user3.setPhone_number("4105551212");
        user3.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant());
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Category category = mock(Category.class);
        when(category.getUpdatedBy()).thenReturn(user3);
        when(category.getCreatedBy()).thenReturn(user2);
        when(category.getIsHome()).thenReturn(true);
        when(category.getIsPublished()).thenReturn(true);
        when(category.getIsRandomStuff()).thenReturn(true);
        when(category.getIsRental()).thenReturn(true);
        when(category.getIsTbyb()).thenReturn(true);
        when(category.getOrderIndex()).thenReturn(1);
        when(category.getCategoryUrl()).thenReturn("https://example.org/example");
        when(category.getDescription()).thenReturn("The characteristics of someone or something");
        when(category.getIcon()).thenReturn("Icon");
        when(category.getImage()).thenReturn("Image");
        when(category.getName()).thenReturn("Name");
        when(category.getUrl()).thenReturn("https://example.org/example");
        doNothing().when(category).setCreatedAt((Instant) any());
        doNothing().when(category).setId((Integer) any());
        doNothing().when(category).setIsDeleted((Boolean) any());
        doNothing().when(category).setUpdatedAt((Instant) any());
        doNothing().when(category).setCategoryUrl((String) any());
        doNothing().when(category).setCreatedBy((User) any());
        doNothing().when(category).setDescription((String) any());
        doNothing().when(category).setIcon((String) any());
        doNothing().when(category).setImage((String) any());
        doNothing().when(category).setIsHome((Boolean) any());
        doNothing().when(category).setIsPublished((Boolean) any());
        doNothing().when(category).setIsRandomStuff((Boolean) any());
        doNothing().when(category).setIsRental((Boolean) any());
        doNothing().when(category).setIsTbyb((Boolean) any());
        doNothing().when(category).setName((String) any());
        doNothing().when(category).setOrderIndex((Integer) any());
        doNothing().when(category).setUpdatedBy((User) any());
        doNothing().when(category).setUrl((String) any());
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
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
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.save((Category) any())).thenReturn(category);

        User user4 = new User();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreatedAt(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        user4.setEmail("jane.doe@example.org");
        user4.setFullName("Dr Jane Doe");
        user4.setId(1);
        user4.setIsDeleted(true);
        user4.setIsEnabled(true);
        user4.setIsseller(true);
        user4.setPassword("iloveyou");
        user4.setPhone_number("4105551212");
        user4.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setUpdatedAt(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        user4.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Integer) any())).thenReturn(Optional.of(user4));
        UserDaoImpl userDao = new UserDaoImpl(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        CategoryController categoryController = new CategoryController(
                new CategoryServiceImpl(new CategoryDaoImpl(categoryRepository, new ProductDaoImpl(productRepository, null,
                        classificationRepository, tbybRepository, null, null, new RedisTemplate()), userDao)));
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
        ResponseDTO<CategoryDTO> actualAddCategoryResult = categoryController.addCategory(categoryDTO, 123);
        assertEquals("2000", actualAddCategoryResult.getCode());
        assertEquals("Operation completed successfully", actualAddCategoryResult.getMessage());
        assertEquals(HttpStatus.OK, actualAddCategoryResult.getHttpStatus());
        CategoryDTO data = actualAddCategoryResult.getData();
        assertEquals("https://example.org/example", data.getCategoryUrl());
        assertEquals("https://example.org/example", data.getUrl());
        assertEquals("Dr Jane Doe", data.getUpdatedByName());
        assertEquals(1, data.getUpdatedBy().intValue());
        assertEquals(1, data.getOrderIndex().intValue());
        assertEquals("Name", data.getName());
        assertTrue(data.getIsTbyb());
        assertTrue(data.getIsRental());
        assertTrue(data.getIsRandomStuff());
        assertTrue(data.getIsPublished());
        assertTrue(data.getIsHome());
        assertEquals("Image", data.getImage());
        assertEquals("Icon", data.getIcon());
        assertEquals("The characteristics of someone or something", data.getDescription());
        assertEquals("Dr Jane Doe", data.getCreatedByName());
        assertEquals(1, data.getCreatedBy().intValue());
        verify(categoryRepository).save((Category) any());
        verify(category, atLeast(1)).getCreatedBy();
        verify(category, atLeast(1)).getUpdatedBy();
        verify(category).getIsHome();
        verify(category).getIsPublished();
        verify(category).getIsRandomStuff();
        verify(category).getIsRental();
        verify(category).getIsTbyb();
        verify(category).getOrderIndex();
        verify(category).getCategoryUrl();
        verify(category).getDescription();
        verify(category).getIcon();
        verify(category).getImage();
        verify(category).getName();
        verify(category).getUrl();
        verify(category).setCreatedAt((Instant) any());
        verify(category).setId((Integer) any());
        verify(category).setIsDeleted((Boolean) any());
        verify(category).setUpdatedAt((Instant) any());
        verify(category).setCategoryUrl((String) any());
        verify(category).setCreatedBy((User) any());
        verify(category).setDescription((String) any());
        verify(category).setIcon((String) any());
        verify(category).setImage((String) any());
        verify(category).setIsHome((Boolean) any());
        verify(category).setIsPublished((Boolean) any());
        verify(category).setIsRandomStuff((Boolean) any());
        verify(category).setIsRental((Boolean) any());
        verify(category).setIsTbyb((Boolean) any());
        verify(category).setName((String) any());
        verify(category).setOrderIndex((Integer) any());
        verify(category).setUpdatedBy((User) any());
        verify(category).setUrl((String) any());
        verify(user3).getId();
        verify(user3).getFullName();
        verify(user3).setCreatedAt((Instant) any());
        verify(user3).setId((Integer) any());
        verify(user3).setIsDeleted((Boolean) any());
        verify(user3).setUpdatedAt((Instant) any());
        verify(user3).setEmail((String) any());
        verify(user3).setFullName((String) any());
        verify(user3).setIsEnabled((Boolean) any());
        verify(user3).setIsseller((Boolean) any());
        verify(user3).setPassword((String) any());
        verify(user3).setPhone_number((String) any());
        verify(user3).setStatus((UserStatus) any());
        verify(user3).setUserUuid((String) any());
        verify(userRepository).findById((Integer) any());
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
    void testAddCategory4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of CategoryController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("iloveyou");
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
        user1.setPassword("iloveyou");
        user1.setPhone_number("4105551212");
        user1.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        user1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        User user2 = mock(User.class);
        when(user2.getId()).thenReturn(1);
        when(user2.getFullName()).thenReturn("Dr Jane Doe");
        doNothing().when(user2).setCreatedAt((Instant) any());
        doNothing().when(user2).setId((Integer) any());
        doNothing().when(user2).setIsDeleted((Boolean) any());
        doNothing().when(user2).setUpdatedAt((Instant) any());
        doNothing().when(user2).setEmail((String) any());
        doNothing().when(user2).setFullName((String) any());
        doNothing().when(user2).setIsEnabled((Boolean) any());
        doNothing().when(user2).setIsseller((Boolean) any());
        doNothing().when(user2).setPassword((String) any());
        doNothing().when(user2).setPhone_number((String) any());
        doNothing().when(user2).setStatus((UserStatus) any());
        doNothing().when(user2).setUserUuid((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        user2.setEmail("jane.doe@example.org");
        user2.setFullName("Dr Jane Doe");
        user2.setId(1);
        user2.setIsDeleted(true);
        user2.setIsEnabled(true);
        user2.setIsseller(true);
        user2.setPassword("iloveyou");
        user2.setPhone_number("4105551212");
        user2.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        user2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        User user3 = mock(User.class);
        when(user3.getId()).thenReturn(1);
        when(user3.getFullName()).thenReturn("Dr Jane Doe");
        doNothing().when(user3).setCreatedAt((Instant) any());
        doNothing().when(user3).setId((Integer) any());
        doNothing().when(user3).setIsDeleted((Boolean) any());
        doNothing().when(user3).setUpdatedAt((Instant) any());
        doNothing().when(user3).setEmail((String) any());
        doNothing().when(user3).setFullName((String) any());
        doNothing().when(user3).setIsEnabled((Boolean) any());
        doNothing().when(user3).setIsseller((Boolean) any());
        doNothing().when(user3).setPassword((String) any());
        doNothing().when(user3).setPhone_number((String) any());
        doNothing().when(user3).setStatus((UserStatus) any());
        doNothing().when(user3).setUserUuid((String) any());
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant());
        user3.setEmail("jane.doe@example.org");
        user3.setFullName("Dr Jane Doe");
        user3.setId(1);
        user3.setIsDeleted(true);
        user3.setIsEnabled(true);
        user3.setIsseller(true);
        user3.setPassword("iloveyou");
        user3.setPhone_number("4105551212");
        user3.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant());
        user3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Category category = mock(Category.class);
        when(category.getUpdatedBy()).thenReturn(user3);
        when(category.getCreatedBy()).thenReturn(user2);
        when(category.getIsHome()).thenReturn(true);
        when(category.getIsPublished()).thenReturn(true);
        when(category.getIsRandomStuff()).thenReturn(true);
        when(category.getIsRental()).thenReturn(true);
        when(category.getIsTbyb()).thenReturn(true);
        when(category.getOrderIndex()).thenReturn(1);
        when(category.getCategoryUrl()).thenReturn("https://example.org/example");
        when(category.getDescription()).thenReturn("The characteristics of someone or something");
        when(category.getIcon()).thenReturn("Icon");
        when(category.getImage()).thenReturn("Image");
        when(category.getName()).thenReturn("Name");
        when(category.getUrl()).thenReturn("https://example.org/example");
        doNothing().when(category).setCreatedAt((Instant) any());
        doNothing().when(category).setId((Integer) any());
        doNothing().when(category).setIsDeleted((Boolean) any());
        doNothing().when(category).setUpdatedAt((Instant) any());
        doNothing().when(category).setCategoryUrl((String) any());
        doNothing().when(category).setCreatedBy((User) any());
        doNothing().when(category).setDescription((String) any());
        doNothing().when(category).setIcon((String) any());
        doNothing().when(category).setImage((String) any());
        doNothing().when(category).setIsHome((Boolean) any());
        doNothing().when(category).setIsPublished((Boolean) any());
        doNothing().when(category).setIsRandomStuff((Boolean) any());
        doNothing().when(category).setIsRental((Boolean) any());
        doNothing().when(category).setIsTbyb((Boolean) any());
        doNothing().when(category).setName((String) any());
        doNothing().when(category).setOrderIndex((Integer) any());
        doNothing().when(category).setUpdatedBy((User) any());
        doNothing().when(category).setUrl((String) any());
        category.setCategoryUrl("https://example.org/example");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setCreatedAt(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
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
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        category.setUpdatedAt(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        category.setUpdatedBy(user1);
        category.setUrl("https://example.org/example");
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.save((Category) any())).thenReturn(category);

        User user4 = new User();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreatedAt(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        user4.setEmail("jane.doe@example.org");
        user4.setFullName("Dr Jane Doe");
        user4.setId(1);
        user4.setIsDeleted(true);
        user4.setIsEnabled(true);
        user4.setIsseller(true);
        user4.setPassword("iloveyou");
        user4.setPhone_number("4105551212");
        user4.setStatus(UserStatus.ACTIVE);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setUpdatedAt(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        user4.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Integer) any())).thenReturn(Optional.of(user4));
        UserDaoImpl userDao = new UserDaoImpl(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        ClassificationRepository classificationRepository = mock(ClassificationRepository.class);
        TBYBRepository tbybRepository = mock(TBYBRepository.class);
        CategoryController categoryController = new CategoryController(
                new CategoryServiceImpl(new CategoryDaoImpl(categoryRepository, new ProductDaoImpl(productRepository, null,
                        classificationRepository, tbybRepository, null, null, new RedisTemplate()), userDao)));
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
        ResponseDTO<CategoryDTO> actualAddCategoryResult = categoryController.addCategory(categoryDTO, 123);
        assertEquals("2000", actualAddCategoryResult.getCode());
        assertEquals("Operation completed successfully", actualAddCategoryResult.getMessage());
        assertEquals(HttpStatus.OK, actualAddCategoryResult.getHttpStatus());
        CategoryDTO data = actualAddCategoryResult.getData();
        assertEquals("https://example.org/example", data.getCategoryUrl());
        assertEquals("https://example.org/example", data.getUrl());
        assertEquals("Dr Jane Doe", data.getUpdatedByName());
        assertEquals(1, data.getUpdatedBy().intValue());
        assertEquals(1, data.getOrderIndex().intValue());
        assertEquals("Name", data.getName());
        assertTrue(data.getIsTbyb());
        assertTrue(data.getIsRental());
        assertTrue(data.getIsRandomStuff());
        assertTrue(data.getIsPublished());
        assertTrue(data.getIsHome());
        assertEquals("Image", data.getImage());
        assertEquals("Icon", data.getIcon());
        assertEquals("The characteristics of someone or something", data.getDescription());
        assertEquals("Dr Jane Doe", data.getCreatedByName());
        assertEquals(1, data.getCreatedBy().intValue());
        verify(categoryRepository).save((Category) any());
        verify(category, atLeast(1)).getCreatedBy();
        verify(category, atLeast(1)).getUpdatedBy();
        verify(category).getIsHome();
        verify(category).getIsPublished();
        verify(category).getIsRandomStuff();
        verify(category).getIsRental();
        verify(category).getIsTbyb();
        verify(category).getOrderIndex();
        verify(category).getCategoryUrl();
        verify(category).getDescription();
        verify(category).getIcon();
        verify(category).getImage();
        verify(category).getName();
        verify(category).getUrl();
        verify(category).setCreatedAt((Instant) any());
        verify(category).setId((Integer) any());
        verify(category).setIsDeleted((Boolean) any());
        verify(category).setUpdatedAt((Instant) any());
        verify(category).setCategoryUrl((String) any());
        verify(category).setCreatedBy((User) any());
        verify(category).setDescription((String) any());
        verify(category).setIcon((String) any());
        verify(category).setImage((String) any());
        verify(category).setIsHome((Boolean) any());
        verify(category).setIsPublished((Boolean) any());
        verify(category).setIsRandomStuff((Boolean) any());
        verify(category).setIsRental((Boolean) any());
        verify(category).setIsTbyb((Boolean) any());
        verify(category).setName((String) any());
        verify(category).setOrderIndex((Integer) any());
        verify(category).setUpdatedBy((User) any());
        verify(category).setUrl((String) any());
        verify(user3).getId();
        verify(user3).getFullName();
        verify(user3).setCreatedAt((Instant) any());
        verify(user3).setId((Integer) any());
        verify(user3).setIsDeleted((Boolean) any());
        verify(user3).setUpdatedAt((Instant) any());
        verify(user3).setEmail((String) any());
        verify(user3).setFullName((String) any());
        verify(user3).setIsEnabled((Boolean) any());
        verify(user3).setIsseller((Boolean) any());
        verify(user3).setPassword((String) any());
        verify(user3).setPhone_number((String) any());
        verify(user3).setStatus((UserStatus) any());
        verify(user3).setUserUuid((String) any());
        verify(user2).getId();
        verify(user2).getFullName();
        verify(user2).setCreatedAt((Instant) any());
        verify(user2).setId((Integer) any());
        verify(user2).setIsDeleted((Boolean) any());
        verify(user2).setUpdatedAt((Instant) any());
        verify(user2).setEmail((String) any());
        verify(user2).setFullName((String) any());
        verify(user2).setIsEnabled((Boolean) any());
        verify(user2).setIsseller((Boolean) any());
        verify(user2).setPassword((String) any());
        verify(user2).setPhone_number((String) any());
        verify(user2).setStatus((UserStatus) any());
        verify(user2).setUserUuid((String) any());
        verify(userRepository).findById((Integer) any());
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
    void testAddBuyCategory2() throws Exception {
        when(this.categoryService.getByFilterCategory(anyBoolean(), anyBoolean(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders
                .post("/v1/api/category/categories/addBuyCategory")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.categoryController)
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
    void testAddRentCategory() throws Exception {
        when(this.categoryService.getByFilterCategory(anyBoolean(), anyBoolean(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/v1/api/category/categories/addRentCategory");
        MockHttpServletRequestBuilder paramResult = postResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.categoryController)
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
    void testAddRentCategory2() throws Exception {
        when(this.categoryService.getByFilterCategory(anyBoolean(), anyBoolean(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders
                .post("/v1/api/category/categories/addRentCategory")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.categoryController)
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
    void testAddTbybCategory() throws Exception {
        when(this.categoryService.getByFilterCategory(anyBoolean(), anyBoolean(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/v1/api/category/categories/addTbybCategory");
        MockHttpServletRequestBuilder paramResult = postResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.categoryController)
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
    void testAddTbybCategory2() throws Exception {
        when(this.categoryService.getByFilterCategory(anyBoolean(), anyBoolean(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders
                .post("/v1/api/category/categories/addTbybCategory")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.categoryController)
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
    void testGetCategoriesByType() throws Exception {
        when(this.categoryService.fetchCategoriesBy((OfferingType) any(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/category/categories");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("type", String.valueOf(OfferingType.TBYB));
        MockMvcBuilders.standaloneSetup(this.categoryController)
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

