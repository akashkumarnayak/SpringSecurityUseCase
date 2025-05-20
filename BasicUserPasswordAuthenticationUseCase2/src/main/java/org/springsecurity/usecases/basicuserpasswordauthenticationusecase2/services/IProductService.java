package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.services;

import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions.ProductDoesNotExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getProducts();
    public Product updateProduct(Product product) throws ProductDoesNotExistException;
    public Product createProduct(Product product);
    public Product deleteProduct(Long productCode) throws ProductDoesNotExistException;

}
