# 📅 Book-Rental (개인 캘린더 웹)

학교 학사 일정을 수집·저장하고, 사용자가 개인 일정을 추가·삭제·조회할 수 있는 캘린더 서비스입니다.  
Spring Boot + JPA 기반으로 구현되었으며, **세션 기반 인증**을 적용해 개인화된 일정 관리 기능을 제공합니다.
<img width="470" height="386" alt="image" src="https://github.com/user-attachments/assets/3e98ddbe-b69f-4b03-82c1-e92bcb42ccfd" />

 🚀 아키텍처
- Spring Boot (API 서버)
- MySQL (RDS or Docker)
- JPA/Hibernate (ORM)
- Session 기반 인증 (Interceptor / Spring Security)
- Swagger (API 명세)

📑 주요 API

| Method | Endpoint                 | 설명            | Auth |
|--------|--------------------------|-----------------|------|
| POST   | /api/auth/login          | 로그인          | X    |
| POST   | /api/auth/logout         | 로그아웃        | O    |
| GET    | /api/personal/events     | 일정 조회       | O    |
| POST   | /api/personal/events     | 일정 추가       | O    |
| DELETE | /api/personal/events/{id}| 일정 삭제       | O    |

예시 요청
```json
POST /api/personal/events
{
  "title": "중간고사",
  "date": "2025-10-01",
  "description": "수치해석 시험"
}
