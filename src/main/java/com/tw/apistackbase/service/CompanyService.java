package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ResponseEntity<String> deleteById(Long id) {
        Optional<Company> companyToDelete = Optional.ofNullable(companyRepository.findOneById(id));

        if(companyToDelete.isPresent()) {
            companyRepository.deleteById(id);
            return new ResponseEntity<>("Deleted ID "+id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Company> updateNameById(Long id, Company company) {
        Optional<Company> companyToUpdate = Optional.ofNullable(companyRepository.findOneById(id));

        if(companyToUpdate.isPresent()) {
            companyToUpdate.get().setName(company.getName());
            companyToUpdate.get().setProfile(company.getProfile());
            companyToUpdate.get().setEmployees(company.getEmployees());

            Company savedCompany = companyRepository.save(companyToUpdate.get());
            return new ResponseEntity<>(savedCompany, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
