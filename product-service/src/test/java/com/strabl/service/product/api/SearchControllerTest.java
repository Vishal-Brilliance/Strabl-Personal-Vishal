package com.strabl.service.product.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.strabl.service.product.service.SearchService;

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

@ContextConfiguration(classes = {SearchController.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class SearchControllerTest {
    @Autowired
    private SearchController searchController;

    @MockBean
    private SearchService searchService;

    @Test
    void testSearchByTagsAndTitle() throws Exception {
        when(this.searchService.searchByTagsAndTitle((String) any(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/search/products");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1)).param("q", "foo");
        MockMvcBuilders.standaloneSetup(this.searchController)
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
    void testSearchByTagsAndTitle2() throws Exception {
        when(this.searchService.searchByTagsAndTitle((String) any(),
                (com.strabl.sdk.common.dto.page.PagedResponseRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/api/search/products")
                .param("pageNumber", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1)).param("q", "foo");
        MockMvcBuilders.standaloneSetup(this.searchController)
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

