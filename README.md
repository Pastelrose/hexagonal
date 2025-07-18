# 헥사고날 아키텍처 프로젝트

이 프로젝트는 헥사고날 아키텍처(Hexagonal Architecture) 패턴을 적용한 Spring Boot 애플리케이션입니다.

## 프로젝트 구조

```
src/main/java/com/ywchoi/hexagonal/
├── adapter
│   ├── in
│   │   └── web
│   │       ├── dto
│   │       │   ├── ExternalApiResponse.java  ← 외부 API 응답 DTO
│   │       │   ├── PostRequest.java
│   │       │   └── PostUpdateRequest.java
│   │       ├── ExternalApiController.java    ← 외부 API 입력 어댑터
│   │       ├── HomeController.java           ← 홈 컨트롤러
│   │       ├── PostController.java           ← REST 입력 어댑터
│   │       └── PostViewController.java       ← 웹 뷰 컨트롤러
│   └── out
│       ├── persistence
│       │   ├── PostJpaEntity.java            ← JPA 엔티티
│       │   ├── PostJpaRepository.java        ← Spring Data JPA
│       │   └── PostRepositoryImpl.java       ← 출력 어댑터
│       └── ExternalApiAdapter.java           ← 외부 API 출력 어댑터
│
├── application
│   └── service
│       ├── ExternalApiService.java           ← 외부 API 유스케이스 구현체
│       └── PostService.java                  ← 게시글 유스케이스 구현체
│
├── domain
│   └── model
│       ├── ExternalData.java                 ← 외부 API 도메인 모델
│       └── Post.java                         ← 게시글 도메인 모델
│
├── port
│   ├── in
│   │   ├── ExternalApiUseCase.java           ← 외부 API 유스케이스 인터페이스
│   │   └── PostUseCase.java                  ← 게시글 유스케이스 인터페이스
│   └── out
│       ├── ExternalApiPort.java              ← 외부 API 포트 인터페이스
│       └── PostRepository.java               ← 도메인 저장소 인터페이스
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
- MySQL 8

## 헥사고날 아키텍처 개요

헥사고날 아키텍처(포트 및 어댑터 아키텍처)는 애플리케이션을 외부 세계와 분리하여 비즈니스 로직을 독립적으로 개발하고 테스트할 수 있게 합니다.

- **도메인(Domain)**: 비즈니스 로직과 엔티티를 포함
- **포트(Port)**: 애플리케이션과 외부 세계 간의 인터페이스 정의
- **어댑터(Adapter)**: 포트 구현체로, 외부 시스템과의 통신 담당
- **애플리케이션(Application)**: 비즈니스 유스케이스 구현

## 실행 방법 (docker)

```bash
docker-compose up --build
```

## 실행 방법 (Gradle)

```bash
./gradlew bootRun
```

## 헥사고날 아키텍처의 특징과 이점

### 1. 핵심 개념

헥사고날 아키텍처는 Alistair Cockburn이 제안한 아키텍처 패턴으로, '포트와 어댑터(Ports and Adapters)' 아키텍처라고도 불립니다. 이 아키텍처는 애플리케이션을 육각형으로 표현하며, 각 변은 외부 세계와의 인터페이스를 나타냅니다.

### 2. 계층 구조

#### 2.1 내부 계층 (Inside)
- **도메인 모델(Domain Model)**: 비즈니스 엔티티와 규칙을 포함합니다. 이 프로젝트에서는 `Post` 클래스가 이에 해당합니다.
- **애플리케이션 서비스(Application Services)**: 유스케이스를 구현하며, 도메인 모델을 조작합니다. 이 프로젝트에서는 `PostService` 클래스가 이에 해당합니다.

#### 2.2 포트 계층 (Ports)
- **입력 포트(Input/Driving Ports)**: 애플리케이션이 외부에 제공하는 API를 정의합니다. 이 프로젝트에서는 `PostUseCase` 인터페이스가 이에 해당합니다.
- **출력 포트(Output/Driven Ports)**: 애플리케이션이 외부 시스템에 접근하기 위한 인터페이스를 정의합니다. 이 프로젝트에서는 `PostRepository` 인터페이스가 이에 해당합니다.

#### 2.3 어댑터 계층 (Adapters)
- **입력 어댑터(Input/Driving Adapters)**: 외부 요청을 애플리케이션의 입력 포트로 변환합니다. 이 프로젝트에서는 `PostController`가 이에 해당합니다.
- **출력 어댑터(Output/Driven Adapters)**: 애플리케이션의 출력 포트를 실제 외부 시스템과 연결합니다. 이 프로젝트에서는 `PostRepositoryImpl`이 이에 해당합니다.

### 3. 의존성 규칙

헥사고날 아키텍처의 핵심 원칙은 의존성 방향입니다:
- 내부 계층은 외부 계층에 의존하지 않습니다.
- 모든 의존성은 내부를 향해야 합니다.
- 도메인 모델은 어떤 외부 프레임워크나 라이브러리에도 의존하지 않습니다.

이 프로젝트에서는:
- `Post` 도메인 모델은 순수한 Java 객체로, 어떤 프레임워크에도 의존하지 않습니다.
- `PostUseCase`와 `PostRepository` 인터페이스는 도메인 계층에 위치하며, 외부 기술에 독립적입니다.
- 어댑터 계층에서만 Spring Framework, JPA 등의 외부 기술을 사용합니다.

### 4. 주요 이점

#### 4.1 테스트 용이성
- 비즈니스 로직을 외부 의존성 없이 단위 테스트할 수 있습니다.
- 모의 객체(Mock)를 사용하여 포트를 쉽게 대체할 수 있습니다.
- 이 프로젝트에서는 `PostService`를 테스트할 때 실제 데이터베이스 대신 Mock `PostRepository`를 사용할 수 있습니다.

#### 4.2 기술 독립성
- 프레임워크, 데이터베이스 등의 기술을 쉽게 교체할 수 있습니다.
- 이 프로젝트에서는 H2에서 MySQL로 데이터베이스를 변경했지만, 도메인 로직은 전혀 수정할 필요가 없었습니다.

#### 4.3 관심사 분리
- 비즈니스 로직과 기술적 구현이 명확히 분리됩니다.
- 각 계층은 단일 책임을 가지며, 변경 이유가 하나만 존재합니다.

#### 4.4 유지보수성
- 코드 구조가 명확하여 새로운 개발자도 쉽게 이해할 수 있습니다.
- 비즈니스 규칙 변경과 기술 스택 변경이 서로 영향을 미치지 않습니다.

### 5. 실제 구현에서의 특징

#### 5.1 도메인 모델의 불변성
- `Post` 클래스는 불변(Immutable) 객체로 설계되어 있습니다.
- 상태 변경이 필요할 때는 새로운 객체를 생성합니다 (`updateContent` 메서드 참조).

#### 5.2 명확한 경계
- 패키지 구조가 아키텍처 계층을 명확히 반영합니다.
- 각 계층 간의 통신은 오직 정의된 포트를 통해서만 이루어집니다.

#### 5.3 의존성 주입
- Spring의 의존성 주입을 활용하여 포트와 어댑터를 연결합니다.
- 런타임에 적절한 구현체가 주입되므로 컴파일 타임 의존성이 줄어듭니다.

### 6. 헥사고날 아키텍처 vs 다른 아키텍처

#### 6.1 전통적인 계층형 아키텍처와의 비교
- 계층형: 수평적 계층(UI → 비즈니스 로직 → 데이터 액세스)으로 구성
- 헥사고날: 중심(도메인)과 외부(어댑터) 간의 관계로 구성

#### 6.2 클린 아키텍처와의 관계
- 헥사고날 아키텍처는 클린 아키텍처의 기본 원칙을 공유합니다.
- 두 아키텍처 모두 의존성 규칙과 관심사 분리를 강조합니다.

#### 6.3 마이크로서비스와의 시너지
- 헥사고날 아키텍처는 마이크로서비스 설계에 이상적입니다.
- 각 서비스가 명확한 경계와 인터페이스를 가지므로 독립적으로 개발 및 배포할 수 있습니다.

### 7. 실제 프로젝트에서의 적용 사례

이 프로젝트에서는 간단한 게시판 기능을 구현했지만, 헥사고날 아키텍처는 다음과 같은 복잡한 시스템에서도 효과적입니다:

- 결제 시스템: 다양한 결제 게이트웨이와 통합
- 알림 서비스: 이메일, SMS, 푸시 알림 등 다양한 채널 지원
- 멀티 테넌트 애플리케이션: 다양한 고객별 설정과 통합 지원

### 8. 주의사항 및 단점

- 작은 프로젝트에서는 오버엔지니어링이 될 수 있습니다.
- 초기 설계와 구현에 더 많은 시간이 필요합니다.
- 개발자가 아키텍처 패턴을 이해하고 규칙을 따라야 합니다.
- 인터페이스와 클래스가 많아져 코드량이 증가할 수 있습니다.

## 외부 API 통신 기능

이 프로젝트에는 외부 API와 통신하여 데이터를 가져오고 가공하는 기능이 추가되었습니다. 이 기능은 헥사고날 아키텍처 패턴에 따라 구현되었습니다.

### 주요 컴포넌트

- **도메인 모델**: `ExternalData` - 외부 API에서 가져온 데이터를 표현하는 도메인 모델
- **입력 포트**: `ExternalApiUseCase` - 외부 API 데이터를 가져오고 처리하는 유스케이스 인터페이스
- **출력 포트**: `ExternalApiPort` - 외부 API와 통신하기 위한 포트 인터페이스
- **애플리케이션 서비스**: `ExternalApiService` - 유스케이스 구현체
- **입력 어댑터**: `ExternalApiController` - 외부 API 데이터를 처리하는 컨트롤러
- **출력 어댑터**: `ExternalApiAdapter` - 외부 API와 통신하는 어댑터 구현체
- **DTO**: `ExternalApiResponse` - 외부 API 응답을 위한 DTO 클래스

### API 엔드포인트

- `GET /api/external/{resourceId}`: 외부 API에서 데이터를 가져와 가공하여 반환
- `POST /api/external/request/{resourceId}`: 외부 API에 데이터를 요청하는 POST 엔드포인트

## 헥사고날 아키텍처 개발 유의사항

헥사고날 아키텍처에 따라 코드를 작성할 때 다음 사항을 유의해야 합니다:

### 1. 도메인 모델 설계

- 도메인 모델은 외부 의존성 없이 순수한 Java 객체로 구현해야 합니다.
- 도메인 모델에는 비즈니스 로직만 포함하고, 인프라스트럭처 관련 코드는 포함하지 않습니다.
- 가능한 불변(Immutable) 객체로 설계하여 상태 변경 시 새로운 객체를 생성하는 방식을 사용합니다.

### 2. 포트 설계

- 포트는 인터페이스로 정의하고, 구체적인 구현은 어댑터에서 합니다.
- 입력 포트(Primary/Driving)는 애플리케이션이 외부에 제공하는 기능을 정의합니다.
- 출력 포트(Secondary/Driven)는 애플리케이션이 외부 시스템에 접근하기 위한 인터페이스를 정의합니다.

### 3. 어댑터 구현

- 어댑터는 특정 기술에 의존적인 코드를 포함할 수 있습니다.
- 입력 어댑터는 외부 요청을 애플리케이션의 입력 포트로 변환합니다.
- 출력 어댑터는 애플리케이션의 출력 포트를 실제 외부 시스템과 연결합니다.
- 어댑터는 도메인 모델과 외부 시스템 간의 데이터 변환을 담당합니다.

### 4. 의존성 방향

- 모든 의존성은 내부(도메인)를 향해야 합니다.
- 도메인 모델은 어댑터나 포트에 의존해서는 안 됩니다.
- 애플리케이션 서비스는 입력 포트를 구현하고 출력 포트에 의존합니다.

### 5. 패키지 구조

- 패키지 구조는 아키텍처 계층을 명확히 반영해야 합니다.
- 각 계층 간의 경계를 명확히 하고, 의존성 규칙을 위반하지 않도록 합니다.
- 도메인 모델, 포트, 어댑터, 애플리케이션 서비스를 별도의 패키지로 분리합니다.

### 6. 테스트

- 도메인 모델과 애플리케이션 서비스는 외부 의존성 없이 단위 테스트가 가능해야 합니다.
- 모의 객체(Mock)를 사용하여 포트를 쉽게 대체할 수 있어야 합니다.
- 통합 테스트는 실제 어댑터를 사용하여 수행합니다.

### 7. 공통 실수 방지

- 도메인 모델에 프레임워크 의존성 추가 금지 (예: JPA 어노테이션)
- 애플리케이션 서비스에서 직접 어댑터 호출 금지
- 출력 포트 없이 직접 외부 시스템 접근 금지
- 도메인 로직을 어댑터나 애플리케이션 서비스에 구현하는 것 금지