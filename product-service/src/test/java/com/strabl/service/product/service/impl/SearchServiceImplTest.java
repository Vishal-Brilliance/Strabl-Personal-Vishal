package com.strabl.service.product.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.PathMappedEndpoints;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class SearchServiceImplTest {
    @MockBean
    private PathMappedEndpoints pathMappedEndpoints;

    @Autowired
    private SearchServiceImpl searchServiceImpl;

    @MockBean
    private WebMvcEndpointHandlerMapping webMvcEndpointHandlerMapping;

    @Test
    void testSearchByTagsAndTitle() {
        assertTrue(
                this.searchServiceImpl.searchByTagsAndTitle("Search Term", new PagedResponseRequest()).toList().isEmpty());
    }
}

