package com.example.webaudit.domain.test.service;

import com.example.toyProject.entity.TestEntity;
import com.example.webaudit.domain.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    @Transactional
    public TestEntity createTest(String url) {
        TestEntity newTest = TestEntity.builder()
                .url(url)
                .status(TestEntity.Status.PENDING)
                .build();
        return testRepository.save(newTest);
    }

    @Transactional(readOnly = true)
    public TestEntity getTestById(String id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test not found with id: " + id));
    }
}