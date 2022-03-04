package com.strabl.service.admin.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.strabl.service.admin.service.AdminService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    @Test
    void testGetAllCustomers() throws Exception {
        when(this.adminService.getAllCustomers((com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/admin/getAllCustomers");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.adminController)
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
    void testGetAllCustomers2() throws Exception {
        when(this.adminService.getAllCustomers((com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/admin/getAllCustomers")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.adminController)
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
    void testGetAllCategory() throws Exception {
        when(this.adminService.getAllCategory((com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/admin/getAllCategories");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.adminController)
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
    void testGetAllCategory2() throws Exception {
        when(this.adminService.getAllCategory((com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/admin/getAllCategories")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.adminController)
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

