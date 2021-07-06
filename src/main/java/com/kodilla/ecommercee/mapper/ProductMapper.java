package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    @Autowired
    private GroupRepository groupRepository;

    public Product mapToProduct(final ProductDto productDto) {

        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getShortDesc(),
                productDto.getLongDesc(),
                productDto.getProductPrice(),
                productDto.getQuantityOnStore(),
                productDto.getProductCondition(),
                productDto.getProductWeight(),
                productDto.isStillOnSale(),
                groupRepository.findById(productDto.getGroupId()).get()
        );
    }

    public ProductDto mapToProductDto(final Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getShortDesc(),
                product.getLongDesc(),
                product.getProductPrice(),
                product.getQuantityOnStore(),
                product.getProductCondition(),
                product.getProductWeight(),
                product.isStillOnSale(),
                product.getGroup().getId()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {

        return products.stream().map(this::mapToProductDto).collect(Collectors.toList());
    }
}
