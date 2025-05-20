package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long productCode;
    private String name;
    private String description;
    private BigDecimal price;
}
