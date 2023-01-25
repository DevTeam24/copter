package com.compter.copter.repos;

import com.compter.copter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPasswordIgnoreCase(String password);

}
