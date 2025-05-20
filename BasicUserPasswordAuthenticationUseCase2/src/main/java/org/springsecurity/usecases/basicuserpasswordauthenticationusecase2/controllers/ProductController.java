package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.dtos.ProductDto;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions.ProductDoesNotExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.Product;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.services.IProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/product/get-all")
    public List<ProductDto> getAllProducts()
    {
        List<Product> products = productService.getProducts();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products)
        {
            productDtos.add(from(product));
        }

        return productDtos;
    }

    @PutMapping("/product/update")
    public ProductDto changeProductDetails(@RequestBody ProductDto productDto) throws ProductDoesNotExistException {
        return from(productService.updateProduct(from(productDto)));
    }

    @PostMapping("/product/create")
    public ProductDto addNewProduct(@RequestBody ProductDto productDto)
    {
        return from(productService.createProduct(from(productDto)));
    }

    @DeleteMapping("/product/delete/{pcode}")
    public ProductDto removeProduct(@PathVariable Long pcode) throws ProductDoesNotExistException {
        return from(productService.deleteProduct(pcode));
    }


    Product from(ProductDto productDto)
    {
        Product product = new Product();
        product.setProductCode(productDto.getProductCode());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;
    }

    ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProductCode(product.getProductCode());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }


}
