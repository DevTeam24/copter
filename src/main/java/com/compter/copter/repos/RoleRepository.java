package com.compter.copter.repos;

import com.compter.copter.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
