package com.tw.apistackbase.repositories;

import com.tw.apistackbase.core.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT a FROM Company a where a.id = :id")
    Company findOneById(@Param("id") Long id);
}
