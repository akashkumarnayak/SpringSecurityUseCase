package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID Id;
    LocalDate createdAt;
    LocalDate lastUpdatedAt;

    BaseModel() {
        createdAt = LocalDate.now();
        lastUpdatedAt = LocalDate.now();
    }
}
