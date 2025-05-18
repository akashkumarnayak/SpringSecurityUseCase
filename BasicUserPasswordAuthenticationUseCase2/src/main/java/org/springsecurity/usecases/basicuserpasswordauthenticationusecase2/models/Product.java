package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Product extends BaseModel {
    private String Name;
    private String Description;
    private BigDecimal price;
}
