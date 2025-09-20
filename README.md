# 🚀 Toy Project - 도메인 실습

Spring Boot를 사용한 웹 오디트 도메인 실습 프로젝트입니다.

## 📋 프로젝트 개요

이 프로젝트는 Spring Boot와 JPA를 활용하여 Test 도메인을 구현하는 실습 프로젝트입니다. URL을 받아서 테스트를 생성하고 조회하는 간단한 API를 제공합니다.

## 🛠️ 기술 스택

- **Java 17**
- **Spring Boot 4.0.0-SNAPSHOT**
- **Spring Data JPA**
- **H2 Database** (인메모리)
- **Gradle**
- **Lombok**

## 📁 프로젝트 구조

```
src/main/java/com/example/
├── toyProject/
│   ├── ToyProjectApplication.java          # 메인 애플리케이션
│   └── entity/
│       └── TestEntity.java                 # Test 엔티티
└── webaudit/
    ├── global/
    │   ├── config/
    │   │   └── WebConfig.java              # CORS 설정
    │   └── error/
    │       ├── exception/
    │       │   └── ErrorCode.java          # 에러 코드 정의
    │       ├── handler/
    │       │   └── GlobalExceptionHandler.java  # 전역 예외 처리
    │       └── model/
    │           └── ErrorResponse.java      # 에러 응답 모델
    └── domain/test/
        ├── controller/
        │   └── TestController.java         # REST API 컨트롤러
        ├── service/
        │   └── TestService.java            # 비즈니스 로직
        ├── repository/
        │   └── TestRepository.java         # 데이터 접근 계층
        ├── dto/
        │   ├── CreateTestRequest.java      # 요청 DTO
        │   └── TestResponse.java           # 응답 DTO
        └── entity/
            └── TestEntity.java             # 도메인 엔티티
```

## 🚀 실행 방법

### 1. 프로젝트 클론
```bash
git clone [repository-url]
cd toyProject
```

### 2. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 3. 애플리케이션 접속
- **애플리케이션**: http://localhost:8080
- **H2 콘솔**: http://localhost:8080/h2-console

## 📚 API 문서

### 1. 테스트 생성
**POST** `/api/v1/tests`

**요청 본문:**
```json
{
  "url": "https://www.google.com"
}
```

**응답:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "status": "PENDING",
  "url": "https://www.google.com"
}
```

### 2. 테스트 조회
**GET** `/api/v1/tests/{id}`

**응답:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "status": "PENDING",
  "url": "https://www.google.com"
}
```

## 🗃️ 데이터베이스 스키마

### Test 테이블
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| id | VARCHAR(36) | UUID (Primary Key) |
| url | VARCHAR(2048) | 테스트할 URL |
| status | VARCHAR(20) | 테스트 상태 (PENDING, RUNNING, DONE, FAILED) |

## 🔧 설정

### application.yml
```yaml
spring:
  h2:
    console:
      enabled: true
      path: /h2-console
  datasource:
    url: jdbc:h2:mem:webauditdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
```

## 🧪 테스트 방법

### POSTMAN 사용
1. **POST 요청**:
   - Method: `POST`
   - URL: `http://localhost:8080/api/v1/tests`
   - Headers: `Content-Type: application/json`
   - Body: `{"url": "https://www.google.com"}`

2. **GET 요청**:
   - Method: `GET`
   - URL: `http://localhost:8080/api/v1/tests/{id}`

### cURL 사용
```bash
# 테스트 생성
curl -X POST http://localhost:8080/api/v1/tests \
  -H "Content-Type: application/json" \
  -d '{"url": "https://www.google.com"}'

# 테스트 조회
curl -X GET http://localhost:8080/api/v1/tests/{id}
```

## ⚠️ 주의사항

- 현재 애플리케이션 시작 시 JPA Repository 스캔 문제로 인해 시작이 실패할 수 있습니다.
- URL 유효성 검증: `http://` 또는 `https://`로 시작하는 URL만 허용됩니다.
- H2 데이터베이스는 인메모리로 동작하므로 애플리케이션 재시작 시 데이터가 초기화됩니다.

## 🐛 알려진 이슈

1. **JPA Repository 스캔 문제**: `TestRepository`가 스캔되지 않아 애플리케이션 시작이 실패합니다.
2. **엔티티 스캔 문제**: `TestEntity`가 관리되는 타입으로 인식되지 않습니다.
