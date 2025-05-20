package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByProductCode(Long productCode);
    void deleteProductByProductCode(Long productCode);

}
