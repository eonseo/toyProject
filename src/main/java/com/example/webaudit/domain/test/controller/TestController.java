package com.example.webaudit.domain.test.controller;

import com.example.webaudit.domain.test.dto.CreateTestRequest;
import com.example.webaudit.domain.test.dto.TestResponse;
import com.example.webaudit.domain.test.entity.TestEntity;
import com.example.webaudit.domain.test.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    public ResponseEntity<TestResponse> createTest(@Valid @RequestBody CreateTestRequest request) {
        TestEntity newTest = testService.createTest(request.getUrl());
        TestResponse response = TestResponse.from(newTest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResponse> getTest(@PathVariable String id) {
        TestEntity test = testService.getTestById(id);
        TestResponse response = TestResponse.from(test);
        return ResponseEntity.ok(response);
    }
}