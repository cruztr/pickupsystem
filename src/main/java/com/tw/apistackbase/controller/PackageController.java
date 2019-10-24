package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.MiniPackage;
import com.tw.apistackbase.service.PackageService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping(produces = {"application/json"})
    public List<MiniPackage> getAllPackages() {
        return packageService.findAll();
    }

    @PostMapping(produces = {"application/json"})
    public MiniPackage add(@RequestBody MiniPackage miniPackage) {
        return packageService.save(miniPackage);
    }
}
