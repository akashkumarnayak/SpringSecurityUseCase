package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.exceptions.ProductAlreadyExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.exceptions.ProductDoesNotExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.Product;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.repos.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) throws ProductDoesNotExistException {

        Optional<Product> dbStoredProductOptional = productRepository.findProductByProductCode(product.getProductCode());

        if (!dbStoredProductOptional.isPresent()) {
            throw new ProductDoesNotExistException("product "+ product.getName()+ "does not exist");
        }

        Product dbStoredProduct = dbStoredProductOptional.get();
        dbStoredProduct.setName(product.getName());
        dbStoredProduct.setDescription(product.getDescription());
        dbStoredProduct.setPrice(product.getPrice());
        dbStoredProduct.setProductCode(product.getProductCode());
        return productRepository.save(dbStoredProduct);
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductByProductCode(product.getProductCode());

        if (productOptional.isPresent()) {
            throw new ProductAlreadyExistException("Product with product code"+ String.valueOf(product.getProductCode()) + "already exists");
        }

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product deleteProduct(Long productCode) throws ProductDoesNotExistException {

        Optional<Product> deletedProductOptional = productRepository.findProductByProductCode(productCode);

        if (!deletedProductOptional.isPresent()) {
            throw new ProductDoesNotExistException("product with code"+ String.valueOf(productCode) + "does not exist");
        }

        productRepository.deleteProductByProductCode(productCode);
        return deletedProductOptional.get();
    }
}
