package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Iterable<Company> findAll(PageRequest name) {
        return companyRepository.findAll(name);
    }

    public Company findOneById(Long id) {
        return companyRepository.findOneById(id);
    }

    public Company findByNameContaining(String name) {
        return companyRepository.findByNameContaining(name);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
