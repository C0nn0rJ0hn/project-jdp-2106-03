package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductCondition;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    List<ProductDto> productDtoList = new ArrayList<>();

    @GetMapping(value = "/getProducts")
    public List<ProductDto> getProducts() {
        return productDtoList;
    }

    @GetMapping(value = "/getProduct")
    public ProductDto getProduct(@RequestParam Long productId) {
        return new ProductDto(1L, "Name", "shortDesc", "longDesc", 6500.00,
                3, ProductCondition.NEW, 20.00, true);
    }

    @DeleteMapping(value = "/deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {

    }

    @PutMapping(value = "/updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(1L, "Edited Name", "Edited shortDesc", "Edited longDesc", 5500.00,
                3, ProductCondition.OUTLET, 20.00, true);
     }

    @PostMapping(value = "/addProduct")
    public void addProduct(@RequestBody ProductDto productDto) {
        ProductDto productDto1 = new ProductDto(2L, "Name2", "shortDesc2", "longDesc2", 3590.99,
                0, ProductCondition.NEW, 15.50, true);
        productDtoList.add(productDto1);
    }
}
