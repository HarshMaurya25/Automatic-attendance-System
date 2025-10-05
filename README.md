# Automatic Attendance System (Spring Boot)

Automatic attendance system is the attendence and student management system build in springboot. It provide easy way to record the attendance and manage it. It provode student management and database for all important information. It have Qr based scanning for attendance that is fast and secure.

Features
- Dynamic Qr based
- jwt based authentication
- Role based access
- Password encoding
- Get Student detail, registration and login
- Get Student attendance by date between
- Get Attendance of Division
- Get Teacher Details
- Create Division and get all division


Compact Spring Boot backend for attendance management with JWT auth and STOMP WebSocket realtime updates.

Tech stack
- Java + Spring Boot (Security, Data JPA, WebSocket/STOMP)
- JPA / relational DB (Postgres/MySQL/H2)
- JWT for stateless auth
- SockJS / STOMP for realtime notifications

Quick start (dev)
1. Clone:
   git clone https://github.com/HarshMaurya25/Automatic-Attendance-System-SpringBoot.git
2. Configure application.properties or env:
   - spring.datasource.url=jdbc:postgresql://localhost:5432/attendance
   - spring.datasource.username=youruser
   - spring.datasource.password=yourpass
   - JWT_SECRET=your-base64-or-secret
3. Build & run:
   mvn clean package
   java -jar target/*.jar

Runtime variables
- JWT_SECRET — HMAC/RSA secret (set via env or CI secrets).
- Datasource URL / credentials.

Primary flows
- Login/Register → AuthenticationManager → JwtService → return token
- Protected request → SecurityFilterChain → JwtFilter → UserDetailService → SecurityContext
- Controller → Service (@Transactional where needed) → Repository → DB
- WebSocket: handshake → HandshakeInterceptor → STOMP CONNECT → ChannelInterceptor → set Principal → message handlers

Docs & diagrams
- See docs/diagrams for PlantUML flow diagrams.

Contributing
- Read CONTRIBUTING.md and CODE_OF_CONDUCT.md before contributing.
- Label issues `good-first-issue` for small starters.

License
- MIT License — see LICENSE.

Contact
- Repo owner: HarshMaurya25 (GitHub)
