package com.compter.copter.repos;

import com.compter.copter.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<File, Long> {
}
