package com.tw.apistackbase.repositories;

import com.tw.apistackbase.model.MiniPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<MiniPackage, Integer> {

}
