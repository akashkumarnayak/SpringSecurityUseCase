package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUsersByEmail(String email);
}
