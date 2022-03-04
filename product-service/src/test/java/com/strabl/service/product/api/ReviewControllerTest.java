package com.strabl.service.product.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strabl.sdk.common.dto.request.ProductReviewRequest;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;
import com.strabl.service.product.service.ReviewService;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ReviewController.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class ReviewControllerTest {
    @Autowired
    private ReviewController reviewController;

    @MockBean
    private ReviewService reviewService;

    @Test
    void testAddReviewAgainstProduct() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/v1/api/review/product/{productSlug}", "Product Slug")
                .param("reviewerUserId", "https://example.org/example")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ProductReviewRequest((short) 1, "Review")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reviewController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetUnapprovedReviews() throws Exception {
        when(this.reviewService.getUnapprovedReviews((com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/review/reviews/unapproved");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.reviewController)
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
    void testGetUnapprovedReviews2() throws Exception {
        when(this.reviewService.getUnapprovedReviews((com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/api/review/reviews/unapproved")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.reviewController)
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
    void testApproveProductReview() throws Exception {
        when(this.reviewService.updateApprovalStatus((Integer) any(), anyBoolean()))
                .thenReturn(mock(ProductReviewResponse.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/v1/api/review/{reviewId}/approve",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reviewController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testApproveProductReview2() throws Exception {
        when(this.reviewService.updateApprovalStatus((Integer) any(), anyBoolean()))
                .thenReturn(mock(ProductReviewResponse.class));
        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch("/v1/api/review/{reviewId}/approve", 123);
        patchResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reviewController)
                .build()
                .perform(patchResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    @Test
    void testDisapproveProductReview() throws Exception {
        when(this.reviewService.updateApprovalStatus((Integer) any(), anyBoolean()))
                .thenReturn(mock(ProductReviewResponse.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/v1/api/review/{reviewId}/disapprove",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reviewController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testDisapproveProductReview2() throws Exception {
        when(this.reviewService.updateApprovalStatus((Integer) any(), anyBoolean()))
                .thenReturn(mock(ProductReviewResponse.class));
        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch("/v1/api/review/{reviewId}/disapprove",
                123);
        patchResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reviewController)
                .build()
                .perform(patchResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    @Test
    void testGetReviewsAgainstProduct() throws Exception {
        when(this.reviewService.getReviewsBy((String) any(), (com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/review/product/{productSlug}",
                "Product Slug");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.reviewController)
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
    void testGetReviewsAgainstProduct2() throws Exception {
        when(this.reviewService.getReviewsBy((String) any(), (com.strabl.sdk.common.dto.page.PagedResponseRequest) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders
                .get("/v1/api/review/product/{productSlug}", "Product Slug")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.reviewController)
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

