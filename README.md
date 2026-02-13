# 🗓️Schedule App Develop

Spring Boot + Spring Data JPA 기반의 **일정 관리 API 서버**입니다.  
유저(회원) 기능과 일정 CRUD, 댓글 기능을 제공하며, 일부 조회 API에 페이지네이션을 적용했습니다.

---

## 사용한 기술

- Java 17
- Spring Boot
- Spring Web (REST API)
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Validation
- 비밀번호 해시(PasswordEncoder 커스텀 구현)
- Pagination

---

## 주요 기능 요약

### 1) User (회원)
- 회원가입(REGISTER)
  - 이메일 중복 체크
  - 비밀번호 암호화(해시) 저장
    
- 로그인(LOGIN)
  - 이메일 유효성 검증
  - 비밀번호 매칭 검증
  - 로그인 성공 시 SessionUser 부여

### 2) Schedule (일정)
- 일정 CRUD
  - 생성 / 전체조회 / 단건조회 / 수정 / 삭제
- User : Schedule = **1 : N 연관관계**
  - 일정은 작성 유저명 문자열 대신 `user_id(FK)`로 유저를 참조
- Auditing(작성일/수정일)
  - createdAt / modifiedAt 자동 관리 (JPA Auditing) 

### 3) Comment
- 댓글 생성, 조회 기능
- Comment는 Schedule, User와 연관관계로 연결(외래키)

### 4) Pagination (페이지네이션)
- 일정 목록 전체 조회에 `Pageable` 적용
- 기본값:
  - size = 5
  - sort = modifiedAt DESC
- 예: `GET /schedulesPage?page=0&size=5&sort=modifiedAt,desc`

---

## 동작 방식(요약)

1. 클라이언트가 회원가입 요청을 보낸다.
2. 서버는 이메일 중복 여부를 확인하고 비밀번호를 해시화해 User를 저장한다.
3. 로그인 요청 시 이메일/비밀번호를 검증하고(성공 시) 세션용 응답(SessionUser)을 내려준다. ⚠️
4. 일정 생성 시 `userId`를 기반으로 User를 조회한 뒤 Schedule에 연관관계를 설정하여 저장한다.
5. 일정 목록 조회는 기본 조회(List) 또는 페이지네이션(Page) 방식으로 제공된다.
6. createdAt/modifiedAt은 Auditing으로 자동 세팅된다.

---

## Error Handling (예외 처리)
- 이미 존재하는 이메일: 409 Conflict
- 로그인 실패(인증 실패): 401 Unauthorized
- 리소스 없음(유저/일정): 404 Not Found
- 잘못된 요청값(page < 0, size <= 0 등): 400 Bad Request
- 서버 내부 오류: 500 Internal Server Error

---

## Sample Request

### Pagination
`GET /schedulesPage?page=0&size=5&sort=modifiedAt,desc`

응답은 Page 구조로 반환:
- content (데이터 배열)
- totalPages / totalElements / number / size 등 메타데이터 포함

---

## API 명세서
https://nostalgic-animantarx-de5.notion.site/CH-3-API-305b42bce2db80b8b551c27d3bcd1b13

---

## ERD
<img width="518" height="1534" alt="image" src="https://github.com/user-attachments/assets/1fdad0a6-b924-4fa3-b0bd-3f3e329b8964" />

