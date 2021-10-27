package br.com.compasso.productapi.utils;

import br.com.compasso.productapi.model.Product;
import br.com.compasso.productapi.model.dto.ProductDTO;

import static br.com.compasso.productapi.utils.TestConstants.*;

public final class TestDataCreator {

    private TestDataCreator() {}

    public static ProductDTO createProductDTO() {
        return new ProductDTO(DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_DESCRIPTION, DEFAULT_PRODUCT_PRICE);
    }

    public static Product createProduct() {
        return new Product(DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_DESCRIPTION, DEFAULT_PRODUCT_PRICE);
    }
}
