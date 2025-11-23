# 🍀 Shamrock Tales

> **"하루의 한 줄이, 아일랜드 설화가 되는 순간"**

부담 없이 남기는 짧은 일상을, AI가 **Seanchaí**(아일랜드 이야기꾼) 스타일의 설화로 변환해
가족만의 이야기(서사)로 쌓아가는 웹서비스입니다.

---

## 📹 데모 영상

https://github.com/user-attachments/assets/f7f2d049-f043-460b-ac02-3180f76f6adc

## 📖 서비스 소개

아일랜드는 오랜 세월 **말로 기억을 남기던 나라**였습니다.
마을마다 **Seanchaí**가 사람들의 삶을 받아 기록하고 의미를 붙여줬습니다.

**Shamrock Tales**는 이 문화적 원형을 현대적으로 재해석해,
부모가 아이와의 하루를 "**의미 있는 설화**"로 남길 수 있는 서비스로 만들었습니다.

### ✨ 핵심 가치

- ✅ '**기록 중심**'이 아니라 **'해석 중심'**
- ✅ **단 한 줄**이면 충분한 기록 UX
- ✅ **아일랜드 구전설화** 세계관 기반 서사화
- ✅ **FAITH / HOPE / LOVE** 세잎클로버로 감정적 의미 분류

---

## 🎯 주요 기능

### 🍀 메인 페이지 - 세잎 클로버 UI

- 세잎 클로버 UI로 지나칠 수 있는 일상을 **일상 속 행복**으로 전환한다는 서비스 메시지 전달
- 3개 감정 키워드(**용기, 소망, 사랑**)에 해당하는 기록 개수를 직관적으로 표시

### ✍️ 일상 기록

- 사용자가 자신의 **육아 에피소드**를 간단하게 기록
- 3개 감정 키워드(**FAITH, HOPE, LOVE**) 선택
- 감정 키워드 선택을 통해 **하루를 긍정적 관점으로 재해석**하도록 유도
- **한두 줄만으로도** 충분히 기록을 완성할 수 있어 부담 감소

### 📚 설화 변환 (AI)

- 사용자의 입력값을 **아일랜드 설화**로 변환
- 짧은 기록을 아일랜드식 구전설화로 재해석해 **가족만의 이야기**로 보존
- 설화 기록 후 메인화면 **클로버 UI 애니메이션**으로 피드백 제공

### 🗂️ 기록 아카이브

- 사용자가 저장한 **기록 원본**과 **변환된 설화**를 아카이빙
- 3개 감정 키워드(**FAITH, HOPE, LOVE**)에 따라 필터링 가능
- 커서 기반 페이지네이션으로 무한 스크롤 지원

---

## 👥 팀 정보

**SOPT 37기 솝커톤 - WEB3 팀**

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Kimgyuilli">
        <img src="https://github.com/Kimgyuilli.png" width="120px;" alt="김규일"/><br />
        <sub><b>김규일</b></sub>
      </a><br />
      <sub>🎯 Server Lead</sub>
    </td>
    <td align="center">
      <a href="https://github.com/ssyoung02">
        <img src="https://github.com/ssyoung02.png" width="120px;" alt="조서영"/><br />
        <sub><b>조서영</b></sub>
      </a><br />
      <sub>💻 Developer</sub>
    </td>
  </tr>
</table>

---

## 🛠️ 기술 스택

### Backend

- **Java 17**
- **Spring Boot 3.3.5**
- **Spring Data JPA**
- **Spring AI** (OpenAI Integration)
- **MySQL**

### Documentation

- **Swagger/OpenAPI 3.0** - API 문서 자동화

### Utilities

- **Lombok** - 보일러플레이트 코드 감소

---

## 🚀 시작하기

### 사전 요구사항

- Java 17 이상
- MySQL 8.0 이상
- OpenAI API Key

### 실행 방법

```bash
# 프로젝트 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun
```

애플리케이션이 실행되면 `http://localhost:8080`에서 접근 가능합니다.

### API 문서

Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## 📁 프로젝트 구조

```
src/main/java/sopt/server/web3/
├── domain/
│   ├── diary/          # 일기 도메인
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   └── dto/
│   └── user/           # 사용자 도메인
│       ├── entity/
│       └── repository/
├── global/             # 전역 설정
│   ├── config/
│   ├── exception/
│   └── response/
└── Web3Application.java
```

---

## 🌟 API 주요 엔드포인트

### 📝 다이어리 API

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/diaries` | 다이어리 목록 조회 (커서 페이징) |
| `GET` | `/api/diaries?leafType=FAITH` | 테마별 다이어리 목록 조회 |
| `GET` | `/api/diaries/{diaryId}` | 다이어리 상세 조회 |

### 응답 예시

```json
{
  "status": 200,
  "message": "성공",
  "data": {
    "diaries": [...],
    "nextCursor": 42,
    "hasNext": true,
    "totalCount": 100
  }
}
```

---

## 🎨 감정 키워드 분류

| 키워드 | 영문 | 설명 |
|--------|------|------|
| 🛡️ 용기 | FAITH | 믿음과 용기가 필요했던 순간 |
| ⭐ 소망 | HOPE | 희망과 기대를 품었던 순간 |
| ❤️ 사랑 | LOVE | 사랑과 애정을 느낀 순간 |

---

## 📄 라이선스

This project is licensed under the MIT License.

---

