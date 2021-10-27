package br.com.compasso.productapi.controller;

import br.com.compasso.productapi.exception.ProductNotFoundException;
import br.com.compasso.productapi.service.ProductService;
import br.com.compasso.productapi.utils.TestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static br.com.compasso.productapi.utils.TestDataCreator.createProductDTO;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testGetProductsSuccess() throws Exception {
        this.mockMvc.perform(get("/products")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOneProductSuccess() throws Exception {
        when(productService.getOneProduct(TestConstants.DEFAULT_PRODUCT_ID)).thenReturn(createProductDTO());
        this.mockMvc.perform(get("/products/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOneProductNotFound() throws Exception {
        when(productService.getOneProduct(10L)).thenThrow(new ProductNotFoundException("product not found"));
        this.mockMvc.perform(get("/products/10")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
