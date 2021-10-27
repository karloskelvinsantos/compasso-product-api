package br.com.compasso.productapi.repository;

import br.com.compasso.productapi.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> search(String query, BigDecimal minPrice, BigDecimal maxPrice);
}
