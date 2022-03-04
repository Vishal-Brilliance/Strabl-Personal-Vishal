package com.strabl.service.product.test;

import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.domain.repository.ProductRepository;
import com.strabl.service.product.api.ProductController;
import com.strabl.service.product.service.ProductService;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.UUID;
import org.junit.Ignore;


@SpringBootTest
@ContextConfiguration
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    String endpoint = "/v1/api/user";
    String cifs = "/new";

     ProductService productService;
     MockMvc mockMvc;
     CreateProductRequest createProductRequest;
     @InjectMocks
     ProductController productController;
     @Mock
     ProductResponse productResponse;

     ProductRepository productRepository;

     @Ignore
    @Test
    public void setUp() {
        productResponse = ProductResponse.builder().
                id(1).
                createdAt(Instant.now()).
                description("hi").
                features("good").
                isFeatured(true).
                finalPrice(200L).
                isApproved(true).
                slug("www.pvt.ltd").
                tags("hello").
                title("behaviour").
                updatedAt(Instant.now()).
                uuid(UUID.randomUUID()).
                build();
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

    }

    @Ignore
    @Test
    public void controllerInitializedCorrectly() {
        Assertions.assertThat(productController).isNotNull();
    }

    @Ignore
    @Test
    public void addProductTest() throws Exception {

        mockMvc.perform(post(endpoint + cifs +  + productResponse.getId()))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(productResponse.getId().toString())))
                .andExpect(jsonPath("$.beerName", is("Beer1")));

    }

}
