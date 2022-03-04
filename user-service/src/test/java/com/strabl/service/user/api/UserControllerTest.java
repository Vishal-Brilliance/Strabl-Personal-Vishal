package com.strabl.service.user.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strabl.sdk.common.dto.request.ForgotPasswordRequest;
import com.strabl.sdk.common.dto.request.ResetPasswordRequest;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.domain.dao.StrablSessionDao;
import com.strabl.service.user.service.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private StrablSessionDao strablSessionDao;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void testVerify() throws Exception {
        doNothing().when(this.userService).verify((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/api/user/verify")
                .param("token", "value");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2001</code><message>Congratulations! Your account has been verified.</message>"
                                + "<httpStatus>OK</httpStatus></ResponseDTO>"));
    }

    @Test
    void testLogout() throws Exception {
        doNothing().when(this.userService).logout((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/api/user/logout")
                .header("Authorization", "Bearer ");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<ResponseDTO><code>2003</code><message>Logout completed successfully</message><httpStatus>OK</httpStatus"
                                        + "></ResponseDTO>"));
    }

    @Test
    void testForgotPassword() throws Exception {
        doNothing().when(this.userService).forgotPassword((ForgotPasswordRequest) any());

        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(forgotPasswordRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/user/forgot-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<ResponseDTO><code>2002</code><message>Forgot password email has been sent. Please check your inbox"
                                        + " to reset the password.</message><httpStatus>OK</httpStatus></ResponseDTO>"));
    }

    @Test
    void testResetPassword() throws Exception {
        doNothing().when(this.userService).resetPassword((ResetPasswordRequest) any(), (String) any());

        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setNewPassword("Test@123");
        String content = (new ObjectMapper()).writeValueAsString(resetPasswordRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/user/reset-password")
                .param("forgotPasswordToken", "foo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<ResponseDTO><code>2002</code><message>Password has been reset successfully</message><httpStatus>OK<"
                                        + "/httpStatus></ResponseDTO>"));
    }

    @Test
    void testDeleteUserById() throws Exception {
        doNothing().when(this.userService).deleteUserById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v1/api/user/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><data>User Deleted successfully</data></ResponseDTO>"));
    }

    @Test
    void testDeleteUserById2() throws Exception {
        doNothing().when(this.userService).deleteUserById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/v1/api/user/delete/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><data>User Deleted successfully</data></ResponseDTO>"));
    }

    @Test
    void testUpdateStatus() throws Exception {
        doNothing().when(this.userService)
                .updateStatus((Integer) any(), (com.strabl.sdk.domain.entity.enums.columns.UserStatus) any());
        when(this.strablSessionDao.getUserProfileForSession((String) any())).thenReturn(mock(UserResponseDTO.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/api/user/status/{id}", 1)
                .param("token", "foo");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><data>status changed successfully</data></ResponseDTO>"));
    }

    @Test
    void testGetSellerItems() throws Exception {
        when(this.userService.getSellerItems((Integer) any(), (com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/getSellerItems");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("productId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.userController)
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
    void testGetSellerItems2() throws Exception {
        when(this.userService.getSellerItems((Integer) any(), (com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/api/user/getSellerItems")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("productId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.userController)
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

