# 헥사고날 아키텍처 프로젝트

이 프로젝트는 헥사고날 아키텍처(Hexagonal Architecture) 패턴을 적용한 Spring Boot 애플리케이션입니다.

## 프로젝트 구조

```
src/main/java/com/ywchoi/hexagonal/
├── adapter
│   ├── in
│   │   └── web
│   │       └── PostController.java        ← REST 입력 어댑터
│   └── out
│       └── persistence
│           ├── PostJpaEntity.java         ← JPA 엔티티
│           ├── PostJpaRepository.java     ← Spring Data JPA
│           └── PostRepositoryImpl.java    ← 출력 어댑터
│
├── application
│   └── service
│       └── PostService.java               ← 유스케이스 구현체
│
├── domain
│   └── model
│       └── Post.java                      ← 게시글 도메인 모델
│
├── port
│   ├── in
│   │   └── PostUseCase.java               ← 유스케이스 인터페이스
│   └── out
│       └── PostRepository.java            ← 도메인 저장소 인터페이스
│
├── infrastructure
│   └── config
│       └── JpaConfig.java
│
└── HexagonalApplication.java
```

## 기술 스택

- Java 17
- Spring Boot 4.0.0-SNAPSHOT
- Gradle

## 헥사고날 아키텍처 개요

헥사고날 아키텍처(포트 및 어댑터 아키텍처)는 애플리케이션을 외부 세계와 분리하여 비즈니스 로직을 독립적으로 개발하고 테스트할 수 있게 합니다.

- **도메인(Domain)**: 비즈니스 로직과 엔티티를 포함
- **포트(Port)**: 애플리케이션과 외부 세계 간의 인터페이스 정의
- **어댑터(Adapter)**: 포트 구현체로, 외부 시스템과의 통신 담당
- **애플리케이션(Application)**: 비즈니스 유스케이스 구현

## 실행 방법

```bash
./gradlew bootRun
```

## 테스트 실행

```bash
./gradlew test
```