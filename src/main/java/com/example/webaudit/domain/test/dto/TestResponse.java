package com.example.webaudit.domain.test.dto;

import com.example.toyProject.entity.TestEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestResponse {
    private String id;
    private String url;
    private String status;

    public static TestResponse from(TestEntity entity) {
        return TestResponse.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .status(entity.getStatus().name())
                .build();
    }
}