package com.strabl.service.user;

import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.service.user.api.UserController;
import com.strabl.service.user.service.UserService;
import lombok.Data;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Ignore;


@SpringBootTest
@ContextConfiguration
@RunWith(MockitoJUnitRunner.class)
public class UserApplicationTest {


     UserService userService;

     @InjectMocks
     UserController userController;
     @Mock
     UserResponseDTO userResponseDTO;

    MockMvc mockMvc;

    @Ignore
    @Test
    public void registerTest() throws Exception {
            given(userService.register(any())).willReturn(userResponseDTO);

        String uri = "/v1/api/user/";
        mockMvc.perform(post("uri" + userResponseDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(userResponseDTO.getId().toString())))
                .andExpect(jsonPath("$.firstname", is("kundan")));

    }


}
