package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductMapper mapper;

    @GetMapping(value = "/getProducts")
    public List<ProductDto> getProducts() {
        List<Product> products = service.getProducts();
        return mapper.mapToProductDtoList(products);
    }

    @GetMapping(value = "/getProduct")
    public ProductDto getProduct(@RequestParam java.lang.Long productId) throws ProductNotFoundException {

        return mapper.mapToProductDto(service.getProductById(productId).orElseThrow(ProductNotFoundException::new));
    }

    @DeleteMapping(value = "/deleteProduct")
    public void deleteProduct(@RequestParam java.lang.Long productId) {
        service.deleteProductById(productId);
    }

    @PutMapping(value = "/updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = mapper.mapToProduct(productDto);
        Product savedProduct = service.saveProduct(product);
        return mapper.mapToProductDto(savedProduct);
     }

    @PostMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody ProductDto productDto) {
        service.saveProduct(mapper.mapToProduct(productDto));
    }
}
