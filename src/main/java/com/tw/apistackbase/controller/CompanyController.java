package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public Company getCompany(@PathVariable Long id) {
        return companyRepository.findOneById(id);
    }

    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Company> companyToDelete = Optional.ofNullable(companyRepository.findOneById(id));

        if(companyToDelete.isPresent()) {
            companyRepository.deleteById(id);
            return new ResponseEntity<>("Deleted ID "+id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Company> updateName(@PathVariable Long id, @RequestBody Company company) {
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
