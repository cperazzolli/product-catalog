package com.algaworks.algashop.product.catalog.application.product.query;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductDetailOutputTestDataBuilder {

    private ProductDetailOutputTestDataBuilder() {

    }


    public static ProductDetailOutput.ProductDetailOutputBuilder aProduct() {
        return ProductDetailOutput.builder()
                .id(UUID.randomUUID())
                .addedAt(OffsetDateTime.now())
                .name("Notebook X11")
                .brand("Deep Driver")
                .regularPrice(BigDecimal.valueOf(1500.00))
                .salePrice(BigDecimal.valueOf(1000.00))
                .inStock(true)
                .enabled(true)
                .description("A Gamer Notebook")
                .category(CategoryMinimalOutput.builder()
                        .id(UUID.randomUUID())
                        .name("Notebook")
                        .build());
    }

    public static ProductDetailOutput.ProductDetailOutputBuilder aProduct1() {
        return ProductDetailOutput.builder()
                .id(UUID.randomUUID())
                .addedAt(OffsetDateTime.now())
                .name("Desktop I9000")
                .brand("Deep Driver")
                .regularPrice(BigDecimal.valueOf(3500.00))
                .salePrice(BigDecimal.valueOf(3000.00))
                .inStock(false)
                .enabled(true)
                .description("A Gamer Notebook")
                .category(CategoryMinimalOutput.builder()
                        .id(UUID.randomUUID())
                        .name("Desktop").build());
    }
}
