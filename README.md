# 🧩 OTD_MSA_Back_Life

**OneToday (OTD)** 백엔드 **라이프 서비스(Life Service)** 레포지토리입니다.  
이 서비스는 MSA(Microservice Architecture) 구조에서 **커뮤니티, 운동, 식단, 댓글, 좋아요 등** 사용자의 일상 기록 도메인을 담당합니다.

---

## ⚙️ 기술 스택

| 항목 | 내용 |
|------|------|
| 언어 | Java 17 |
| 프레임워크 | Spring Boot 3.x |
| ORM | JPA / MyBatis 혼용 |
| DB | MariaDB |
| 파일 업로드 | Spring Web + MultipartResolver |
| 빌드 도구 | Gradle |
| 배포 환경 | Docker, Kubernetes |

---

## 🚀 실행 방법

```bash
# 애플리케이션 실행
./gradlew bootRun

---

💡 주요 기능

📝 커뮤니티 게시글 CRUD
💬 댓글 및 대댓글 관리
❤️ 좋아요 기능
📸 이미지 업로드 / 파일 관리
🏋️ 운동 및 식단 기록 (확장 예정)

🧠 서비스 아키텍처
[Gateway]
   ↓
[OTD_MSA_Back_Life]
   ↓
[Community DB / File Storage]
