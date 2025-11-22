# API 테스트 가이드

## 사전 준비
- 애플리케이션 실행: `./gradlew bootRun`
- Base URL: `http://localhost:8080`
- DB 초기 데이터 로드 완료 (data.sql 실행됨)
  - User 1명 (userId=1)
  - Diary 3개 (FAITH, LOVE, HOPE 각 1개)
  - Saga 3개
  - UserSagaCount 1개 (faithCount=1, loveCount=1, hopeCount=1)

---

## 테스트 시나리오

### 1️⃣ 전체 일기 목록 조회

**Request**
```
GET http://localhost:8080/api/diaries
```

**Expected Response (200 OK)**
```json
{
  "code": "S200",
  "message": "성공",
  "data": [
    {
      "diaryId": 3,
      "title": "희망의 빛",
      "leafType": "HOPE",
      "createdAt": "2024-01-17T09:15:00"
    },
    {
      "diaryId": 2,
      "title": "사랑의 순간",
      "leafType": "LOVE",
      "createdAt": "2024-01-16T14:20:00"
    },
    {
      "diaryId": 1,
      "title": "믿음의 하루",
      "leafType": "FAITH",
      "createdAt": "2024-01-15T10:30:00"
    }
  ]
}
```

---

### 2️⃣ 특정 일기 상세 조회 (설화 포함)

**Request**
```
GET http://localhost:8080/api/diaries/1
```

**Expected Response (200 OK)**
```json
{
  "code": "S200",
  "message": "성공",
  "data": {
    "diaryId": 1,
    "title": "믿음의 하루",
    "originalContent": "오늘은 힘든 일이 있었지만, 포기하지 않고 끝까지 해냈다.",
    "sagaContent": "옛날 어느 마을에 한 농부가 살았습니다. 어느 날 큰 폭풍이 마을을 덮쳤지만, 농부는 믿음을 잃지 않았습니다. 그는 매일 밭을 돌보았고, 마침내 풍성한 수확을 거두었습니다.",
    "leafType": "FAITH",
    "createdAt": "2024-01-15T10:30:00"
  }
}
```

---

### 3️⃣ 새로운 일기 작성 및 설화 생성 (FAITH)

**Request**
```
POST http://localhost:8080/api/diary
Content-Type: application/json

{
  "title": "도전의 하루",
  "createdAt": "2024-01-18T16:45:00",
  "theme": "FAITH",
  "content": "새로운 프로젝트를 시작했다. 두렵지만 믿음을 가지고 도전해보려 한다."
}
```

**Expected Response (200 OK)**
```json
{
  "code": "O002",
  "message": "일기를 설화로 변환 성공",
  "data": {
    "diaryId": 4,
    "sagaId": 4,
    "story": "먼 옛날, 어느 마을에 젊은 장인이 살았습니다. 그는 아무도 만들어본 적 없는 새로운 도구를 만들기로 결심했습니다. 주변 사람들은 불가능하다고 했지만, 장인은 자신의 기술을 믿었습니다. 수많은 실패 끝에 마침내 아름다운 작품을 완성했고, 그의 믿음은 마을 사람들에게 용기를 주었습니다."
  }
}
```

**DB 확인사항**
- `user_saga_counts` 테이블에서 `faith_count`가 1 → 2로 증가

---

### 4️⃣ 새로운 일기 작성 및 설화 생성 (LOVE)

**Request**
```
POST http://localhost:8080/api/diary
Content-Type: application/json

{
  "title": "감사의 마음",
  "createdAt": "2024-01-19T11:20:00",
  "theme": "LOVE",
  "content": "가족들과 함께 저녁을 먹었다. 평범한 순간이지만 함께 있다는 것에 감사했다."
}
```

**Expected Response (200 OK)**
```json
{
  "code": "O002",
  "message": "일기를 설화로 변환 성공",
  "data": {
    "diaryId": 5,
    "sagaId": 5,
    "story": "어느 숲속에 동물 가족들이 모여 살았습니다. 매일 저녁이면 모두 함께 모여 하루를 나누었습니다. 작은 토끼는 이 시간이 가장 행복했습니다. 가족의 사랑이 있기에 어떤 어려움도 이겨낼 수 있었기 때문입니다."
  }
}
```

**DB 확인사항**
- `user_saga_counts` 테이블에서 `love_count`가 1 → 2로 증가

---

### 5️⃣ 새로운 일기 작성 및 설화 생성 (HOPE)

**Request**
```
POST http://localhost:8080/api/diary
Content-Type: application/json

{
  "title": "내일을 기대하며",
  "createdAt": "2024-01-20T20:30:00",
  "theme": "HOPE",
  "content": "오늘은 많이 힘들었지만, 내일은 분명 더 나은 날이 올 것이다."
}
```

**Expected Response (200 OK)**
```json
{
  "code": "O002",
  "message": "일기를 설화로 변환 성공",
  "data": {
    "diaryId": 6,
    "sagaId": 6,
    "story": "긴 겨울이 지나고 작은 마을에 봄이 찾아왔습니다. 얼어붙었던 땅에서 새싹이 돋아났고, 사람들은 희망을 품었습니다. 어려운 시간이 있었지만, 결국 밝은 날이 올 것임을 모두가 믿었습니다."
  }
}
```

**DB 확인사항**
- `user_saga_counts` 테이블에서 `hope_count`가 1 → 2로 증가

---

### 6️⃣ 생성된 일기 확인

**Request**
```
GET http://localhost:8080/api/diaries/4
```

**Expected Response (200 OK)**
```json
{
  "code": "S200",
  "message": "성공",
  "data": {
    "diaryId": 4,
    "title": "도전의 하루",
    "originalContent": "새로운 프로젝트를 시작했다. 두렵지만 믿음을 가지고 도전해보려 한다.",
    "sagaContent": "먼 옛날, 어느 마을에 젊은 장인이...",
    "leafType": "FAITH",
    "createdAt": "2024-01-18T16:45:00"
  }
}
```

---

### 7️⃣ 전체 일기 목록 재조회 (생성된 일기 포함)

**Request**
```
GET http://localhost:8080/api/diaries
```

**Expected Response (200 OK)**
```json
{
  "code": "S200",
  "message": "성공",
  "data": [
    {
      "diaryId": 6,
      "title": "내일을 기대하며",
      "leafType": "HOPE",
      "createdAt": "2024-01-20T20:30:00"
    },
    {
      "diaryId": 5,
      "title": "감사의 마음",
      "leafType": "LOVE",
      "createdAt": "2024-01-19T11:20:00"
    },
    {
      "diaryId": 4,
      "title": "도전의 하루",
      "leafType": "FAITH",
      "createdAt": "2024-01-18T16:45:00"
    },
    {
      "diaryId": 3,
      "title": "희망의 빛",
      "leafType": "HOPE",
      "createdAt": "2024-01-17T09:15:00"
    },
    {
      "diaryId": 2,
      "title": "사랑의 순간",
      "leafType": "LOVE",
      "createdAt": "2024-01-16T14:20:00"
    },
    {
      "diaryId": 1,
      "title": "믿음의 하루",
      "leafType": "FAITH",
      "createdAt": "2024-01-15T10:30:00"
    }
  ]
}
```

---

### 8️⃣ UserSagaCount 확인 (DB 직접 조회)

**SQL Query**
```sql
SELECT user_id, faith_count, love_count, hope_count
FROM user_saga_counts
WHERE user_id = 1;
```

**Expected Result (3개 일기 생성 후)**
```
user_id | faith_count | love_count | hope_count
--------|-------------|------------|------------
   1    |      2      |     2      |     2
```

---

## 에러 케이스 테스트

### ❌ 잘못된 테마

**Request**
```
POST http://localhost:8080/api/diary
Content-Type: application/json

{
  "title": "테스트",
  "createdAt": "2024-01-20T20:30:00",
  "theme": "INVALID",
  "content": "테스트 내용"
}
```

**Expected Response (400 Bad Request)**
```json
{
  "code": "C002",
  "message": "데이터 형식이 올바르지 않습니다",
  "data": null
}
```

---

### ❌ 존재하지 않는 일기 조회

**Request**
```
GET http://localhost:8080/api/diaries/999
```

**Expected Response (404 Not Found)**
```json
{
  "code": "D001",
  "message": "다이어리를 찾을 수 없습니다",
  "data": null
}
```

---

### ❌ 필수 필드 누락

**Request**
```
POST http://localhost:8080/api/diary
Content-Type: application/json

{
  "title": "테스트",
  "theme": "FAITH"
}
```

**Expected Response (400 Bad Request)**
```json
{
  "code": "C002",
  "message": "데이터 형식이 올바르지 않습니다",
  "data": null
}
```

---

## Postman Collection Import

아래 JSON을 복사하여 Postman에서 Import하세요:

```json
{
  "info": {
    "name": "Web3 Diary API (with UserSagaCount)",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "1. Get All Diaries",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/diaries",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diaries"]
        }
      }
    },
    {
      "name": "2. Get Diary Detail",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/diaries/1",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diaries", "1"]
        }
      }
    },
    {
      "name": "3. Create Diary with Saga (FAITH)",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"title\": \"도전의 하루\",\n  \"createdAt\": \"2024-01-18T16:45:00\",\n  \"theme\": \"FAITH\",\n  \"content\": \"새로운 프로젝트를 시작했다. 두렵지만 믿음을 가지고 도전해보려 한다.\"\n}"
        },
        "url": {
          "raw": "http://localhost:8080/api/diary",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diary"]
        }
      }
    },
    {
      "name": "4. Create Diary with Saga (LOVE)",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"title\": \"감사의 마음\",\n  \"createdAt\": \"2024-01-19T11:20:00\",\n  \"theme\": \"LOVE\",\n  \"content\": \"가족들과 함께 저녁을 먹었다. 평범한 순간이지만 함께 있다는 것에 감사했다.\"\n}"
        },
        "url": {
          "raw": "http://localhost:8080/api/diary",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diary"]
        }
      }
    },
    {
      "name": "5. Create Diary with Saga (HOPE)",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"title\": \"내일을 기대하며\",\n  \"createdAt\": \"2024-01-20T20:30:00\",\n  \"theme\": \"HOPE\",\n  \"content\": \"오늘은 많이 힘들었지만, 내일은 분명 더 나은 날이 올 것이다.\"\n}"
        },
        "url": {
          "raw": "http://localhost:8080/api/diary",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diary"]
        }
      }
    },
    {
      "name": "6. Get Created Diary Detail",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/diaries/4",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diaries", "4"]
        }
      }
    },
    {
      "name": "7. Get All Diaries (After Creation)",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/diaries",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diaries"]
        }
      }
    },
    {
      "name": "Error: Invalid Theme",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"title\": \"테스트\",\n  \"createdAt\": \"2024-01-20T20:30:00\",\n  \"theme\": \"INVALID\",\n  \"content\": \"테스트 내용\"\n}"
        },
        "url": {
          "raw": "http://localhost:8080/api/diary",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diary"]
        }
      }
    },
    {
      "name": "Error: Diary Not Found",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/diaries/999",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "diaries", "999"]
        }
      }
    }
  ]
}
```

---

## 테스트 순서 권장

1. **GET /api/diaries** - 초기 데이터 확인 (3개)
2. **GET /api/diaries/1** - 설화가 포함된 일기 상세 확인
3. **DB 확인** - UserSagaCount 초기값 확인 (faith:1, love:1, hope:1)
4. **POST /api/diary** (FAITH) - 새 일기 생성 및 설화 자동 생성
5. **DB 확인** - UserSagaCount의 faithCount 증가 확인 (1→2)
6. **POST /api/diary** (LOVE) - 사랑 테마 일기 생성
7. **DB 확인** - loveCount 증가 확인 (1→2)
8. **POST /api/diary** (HOPE) - 희망 테마 일기 생성
9. **DB 확인** - hopeCount 증가 확인 (1→2)
10. **GET /api/diaries** - 전체 목록에 6개 일기가 있는지 확인
11. **에러 케이스** - 잘못된 테마, 존재하지 않는 일기 조회 등

---

## UserSagaCount 동작 확인 방법

### 방법 1: SQL 직접 조회
```sql
SELECT * FROM user_saga_counts WHERE user_id = 1;
```

### 방법 2: 테스트 시나리오
1. 초기 상태: faith=1, love=1, hope=1
2. FAITH 일기 1개 생성 → faith=2
3. LOVE 일기 1개 생성 → love=2
4. HOPE 일기 1개 생성 → hope=2
5. FAITH 일기 1개 더 생성 → faith=3

각 일기 생성 후 DB를 확인하여 해당 카운트가 정확히 증가했는지 검증합니다.

---

## Swagger UI 대안

브라우저에서 더 쉽게 테스트하려면:
```
http://localhost:8080/swagger-ui.html
```

---

## 주요 기능 정리

### 자동으로 처리되는 사항
1. **사용자 ID 고정**: 모든 요청은 userId=1로 자동 처리
2. **설화 자동 생성**: OpenAI API를 통해 테마별로 다른 설화 생성
3. **카운트 자동 증가**: 일기 생성 시 해당 테마의 UserSagaCount 자동 증가
4. **트랜잭션 관리**: Diary, Saga, UserSagaCount가 하나의 트랜잭션으로 처리
5. **생성일시 관리**: BaseTimeEntity를 통한 자동 생성/수정 시간 기록

### 테마별 프롬프트 특징
- **FAITH (믿음)**: 신뢰와 믿음의 가치를 담은 설화
- **LOVE (사랑)**: 사랑과 관계의 소중함을 담은 설화
- **HOPE (희망)**: 희망과 긍정의 메시지를 담은 설화
