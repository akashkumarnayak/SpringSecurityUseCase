package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Product extends BaseModel {

    @Column(unique = true, nullable = false)
    private Long productCode;

    private String Name;
    private String Description;
    private BigDecimal price;
}
