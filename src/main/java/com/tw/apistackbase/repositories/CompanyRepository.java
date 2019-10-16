package com.tw.apistackbase.repositories;

import com.tw.apistackbase.core.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findOneById(Long id);
}
