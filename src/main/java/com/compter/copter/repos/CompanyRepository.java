package com.compter.copter.repos;

import com.compter.copter.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long> {
}
