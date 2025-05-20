package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByProductCode(Long productCode);
    void deleteProductByProductCode(Long productCode);

}
