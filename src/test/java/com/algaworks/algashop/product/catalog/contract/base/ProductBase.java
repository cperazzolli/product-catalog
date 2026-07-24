package com.algaworks.algashop.product.catalog.contract.base;

import com.algaworks.algashop.product.catalog.application.ResourceNotFoundException;
import com.algaworks.algashop.product.catalog.application.product.management.ProductManagementApplicationService;
import com.algaworks.algashop.product.catalog.application.product.query.*;
import com.algaworks.algashop.product.catalog.presentation.ProductController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ProductController.class)
class ProductBase {

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private ProductQueryService productQueryService;

    @MockitoBean
    private ProductManagementApplicationService productManagementApplicationService;

    private static final UUID productValid = UUID.fromString("bb99e5b9-8cea-49cb-a4e1-32868193cd6b");
    private static final UUID createProductId = UUID.fromString("dd6159a8-4b4b-4282-b3cb-f8155578089e");
    private static final UUID invalidProductId = UUID.fromString("bb7259ca-fa7f-4807-8ad4-7df3fd5e174f");

    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build());

        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        mockInvalidOrderFindById();
        mockFilterProdducts();
        mockCreateProduct();
        mockInvalidProductFindById();
    }

    private void mockInvalidProductFindById() {
        when(productQueryService.findById(invalidProductId))
                .thenThrow(new ResourceNotFoundException());
    }

    private void mockCreateProduct() {
        when(productManagementApplicationService.create(any()))
                .thenReturn(createProductId);
        when(productQueryService.findById(createProductId))
                .thenReturn(ProductDetailOutputTestDataBuilder.aProduct()
                        .id(createProductId)
                        .inStock(false)
                        .build());
    }
    private void mockFilterProdducts() {
        when(productQueryService.filter(any(),any()))
                .then((anser) -> {
                    Integer size = anser.getArgument(0);
                    return PageModel.<ProductDetailOutput>builder()
                            .number(0)
                            .size(size)
                            .totalPages(1)
                            .totalElements(1)
                            .content(
                                    List.of(
                                            ProductDetailOutputTestDataBuilder.aProduct().build(),
                                            ProductDetailOutputTestDataBuilder.aProduct1().build()
                                    )).build();
                });
    }

    private void mockInvalidOrderFindById() {
        when(productQueryService.findById(productValid))
                .thenReturn(ProductDetailOutputTestDataBuilder.aProduct()
                        .id(productValid)
                        .build());
    }

}
