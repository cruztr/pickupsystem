package com.tw.apistackbase.service;

import com.tw.apistackbase.model.MiniPackage;
import com.tw.apistackbase.repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    PackageRepository packageRepository;

    public List<MiniPackage> findAll() {
        return packageRepository.findAll();
    }

    public MiniPackage save(MiniPackage miniPackage) {
        return packageRepository.save(miniPackage);
    }
}
