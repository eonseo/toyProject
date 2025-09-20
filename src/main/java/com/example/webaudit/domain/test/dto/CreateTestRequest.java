package com.example.webaudit.domain.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreateTestRequest {

    @NotBlank(message = "URL은 필수 입력 값입니다.")
    @Pattern(regexp = "^(http|https)://.*", message = "유효하지 않은 URL 형식입니다. http 또는 https로 시작해야 합니다.")
    private String url;
}