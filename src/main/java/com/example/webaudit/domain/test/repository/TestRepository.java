package com.example.webaudit.domain.test.repository;

import com.example.toyProject.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, String> {
}