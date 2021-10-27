package br.com.compasso.productapi.service;

import br.com.compasso.productapi.exception.ProductNotFoundException;
import br.com.compasso.productapi.model.Product;
import br.com.compasso.productapi.model.dto.ProductDTO;
import br.com.compasso.productapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    public static final String MESSAGE_PRODUCT_NOT_FOUND = "Product not found";

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductDTO::mapperToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        var productPersisted =  repository.save(Product.mapperToProduct(productDTO));

        return ProductDTO.mapperToProductDTO(productPersisted);
    }

    public ProductDTO getOneProduct(Long id) {
        return ProductDTO.mapperToProductDTO(repository.findById(id).orElseThrow(() -> new ProductNotFoundException(MESSAGE_PRODUCT_NOT_FOUND)));
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        var findedProduct = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(MESSAGE_PRODUCT_NOT_FOUND));

        findedProduct.setName(productDTO.getName());
        findedProduct.setDescription(productDTO.getDescription());
        findedProduct.setPrice(productDTO.getPrice());

        return ProductDTO.mapperToProductDTO(repository.save(findedProduct));
    }

    public void deleteProduct(Long id) {
        var findedProduct = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(MESSAGE_PRODUCT_NOT_FOUND));

        repository.delete(findedProduct);
    }

    public List<ProductDTO> searchProduct(String q, BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.search(q, minPrice, maxPrice)
                .stream()
                .map(ProductDTO::mapperToProductDTO)
                .collect(Collectors.toList());
    }
}
