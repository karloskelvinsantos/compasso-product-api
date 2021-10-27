package br.com.compasso.productapi.repository;

import br.com.compasso.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    List<Product> search(String query, BigDecimal minPrice, BigDecimal maxPrice);
}
