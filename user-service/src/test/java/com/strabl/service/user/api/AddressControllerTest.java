package com.strabl.service.user.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strabl.sdk.domain.entity.Address;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import com.strabl.service.user.service.AddressService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AddressController.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class AddressControllerTest {
    @Autowired
    private AddressController addressController;

    @MockBean
    private AddressService addressService;

    @Test
    void testAddaddress() throws Exception {
        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setId(1);
        user.setIsDeleted(true);
        user.setIsEnabled(true);
        user.setIsseller(true);
        user.setPassword("Test@321");
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
        String content = (new ObjectMapper()).writeValueAsString(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/address/addaddress")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testDeleteAddressById() throws Exception {
        doNothing().when(this.addressService).deleteAddressById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v1/api/address/deleteaddress/{id}",
                1);
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><data>Address Deleted successfully</data></ResponseDTO>"));
    }

    @Test
    void testDeleteAddressById2() throws Exception {
        doNothing().when(this.addressService).deleteAddressById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/v1/api/address/deleteaddress/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ResponseDTO><code>2000</code><message>Operation completed successfully</message><httpStatus>OK<"
                                + "/httpStatus><data>Address Deleted successfully</data></ResponseDTO>"));
    }

    @Test
    void testGetAddressById() throws Exception {
        when(this.addressService.getAddressById((Integer) any(), (Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/address/getaddress");
        MockHttpServletRequestBuilder paramResult = getResult.param("id", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("user_id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }
}

