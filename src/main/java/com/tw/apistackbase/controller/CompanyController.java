package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        companyRepository.deleteById(id);
        return ResponseEntity.ok("Deleted ID " + id);
    }

    @PatchMapping(value = "/{id}", produces = {"application/json"})
    public Company updateName(@PathVariable Long id, @RequestBody Company company) {
        Company companyToUpdate = companyRepository.findOneById(id);

        companyToUpdate.setName(company.getName());
        companyToUpdate.setProfile(company.getProfile());
        companyToUpdate.setEmployees(company.getEmployees());

        companyRepository.save(companyToUpdate);

        return  companyToUpdate;
    }
}
