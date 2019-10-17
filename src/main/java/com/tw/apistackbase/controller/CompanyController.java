package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repositories.CompanyRepository;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list(@RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer pageSize) {
        return companyService.findAll(PageRequest.of(page, pageSize, Sort.by("name").ascending()));
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public Company getCompany(@PathVariable Long id) {
        return companyService.findOneById(id);
    }

    @GetMapping(produces = {"application/json"}, params = "name")
    public Company getCompany(@RequestParam(required = false) String name) {
        return companyService.findByNameContaining(name);
    }

    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return companyService.save(company);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<String> delete(@PathVariable Long id){
        return companyService.deleteById(id);
    }

    @PatchMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Company> updateName(@PathVariable Long id, @RequestBody Company company) {
        return companyService.updateNameById(id, company);
    }
}
