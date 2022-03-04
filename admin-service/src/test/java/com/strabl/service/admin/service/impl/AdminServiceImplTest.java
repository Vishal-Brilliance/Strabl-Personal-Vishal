package com.strabl.service.admin.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.domain.dao.CategoryDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Category;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminServiceImpl.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class AdminServiceImplTest {
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @MockBean
    private CategoryDao categoryDao;

    @MockBean
    private UserDao userDao;

    @Test
    void testGetAllCustomers() {
        when(this.userDao.getAllCustomers((PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.adminServiceImpl.getAllCustomers(new PagedResponseRequest()).toList().isEmpty());
        verify(this.userDao).getAllCustomers((PagedResponseRequest) any());
    }

    @Test
    void testGetAllCategory() {
        PageImpl<Category> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.categoryDao.getAllCategory((PagedResponseRequest) any())).thenReturn(pageImpl);
        Page<Category> actualAllCategory = this.adminServiceImpl.getAllCategory(new PagedResponseRequest());
        assertSame(pageImpl, actualAllCategory);
        assertTrue(actualAllCategory.toList().isEmpty());
        verify(this.categoryDao).getAllCategory((PagedResponseRequest) any());
    }
}

