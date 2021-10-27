package br.com.compasso.productapi.service;

import br.com.compasso.productapi.exception.ProductNotFoundException;
import br.com.compasso.productapi.repository.ProductRepository;
import br.com.compasso.productapi.utils.TestConstants;
import br.com.compasso.productapi.utils.TestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void testGetProductSuccess() {
        var product = TestDataCreator.createProduct();
        when(productRepository.findById(TestConstants.DEFAULT_PRODUCT_ID)).thenReturn(Optional.of(product));

        var productDTO = productService.getOneProduct(TestConstants.DEFAULT_PRODUCT_ID);

        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getDescription(), productDTO.getDescription());
        assertEquals(product.getPrice(), productDTO.getPrice());

        verify(productRepository, times(1)).findById(any());
    }

    @Test
    void testGetProductNotFound() {
        when(productRepository.findById(TestConstants.DEFAULT_PRODUCT_ID)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getOneProduct(TestConstants.DEFAULT_PRODUCT_ID));
    }
}
