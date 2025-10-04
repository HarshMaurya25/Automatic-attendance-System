# Attendance System

This is the backend service for the Attendance System — a Spring Boot application that provides JWT-based authentication, REST endpoints for users, students, teachers and administrators, and a STOMP WebSocket endpoint for real-time lecture QR updates and teacher notifications.

The repository contains:

- A Spring Boot application (Java) with modules for authentication, lecture management, attendance tracking and WebSocket messaging.
- Security with JWT tokens (stateless) and method-level authorization via `@PreAuthorize`.
- STOMP WebSocket endpoint (`/ws`) to handle QR updates and real-time notifications.
- Mapper classes to map between entities and DTOs and JPA repositories for persistence.

This README focuses on setup, security considerations, and developer guidance.

## Quick start (development)

Requirements:

- JDK 17+ (or the version configured in your build)
- Maven

Run locally:

1. Build the project

```bash
mvn -U clean package
```

2. Run the application

```bash
mvn spring-boot:run
```

3. Default endpoints are served on `http://localhost:8080`.

WebSocket endpoint: `ws://localhost:8080/ws` (SockJS/STOMP)

## Project layout

- `src/main/java/com/project/Attendance_System/` — application code
- `src/main/resources` — application.properties, data.sql, templates
- `docs/diagrams` — PlantUML sequence diagrams created to document flows

## Security & JWT

- Public endpoints: `POST /api/v1/public/new` (register) and `POST /api/v1/public/login`.
- All other endpoints require JWT in `Authorization: Bearer <token>` header.
- JWT generation and validation are implemented in `JwtService`.
- `JwtFilter` extracts token from HTTP header and populates `SecurityContext` with a `UserPrincipal`.

WebSocket auth:

- A handshake interceptor (`WebSocketAuthHandshakeInterceptor`) and STOMP channel interceptor
  (`StompAuthenticationChannelInterceptor`) support passing the JWT either in the CONNECT STOMP headers
  or as a query param during the handshake. After a successful CONNECT, the STOMP session has a Principal
  set to an Authentication object. Ensure message handlers check principal/authorities.

## Diagrams

See `docs/diagrams/` for PlantUML sequence diagrams:

- `auth-filter-flow.puml` — JWT filter -> SecurityContext flow
- `create-lecture-flow.puml` — lecture creation and attendance row initialization
- `student-mark-attendance-flow.puml` — student marking attendance flow

You can render these with PlantUML or an online editor.

## Development notes & recommendations

- The active lecture session map is currently in-memory (`LectureService.activeSession`). For clustered deployments replace with Redis or a persisted store.
- Prefer atomic DB updates when marking attendance (use `@Modifying` queries like `markPresent`) to avoid read-write races.
- Add WS handshake validation to reject unauthenticated sockets if you want to prevent anonymous access.
- Fix `@PreAuthorize` expressions to reference `principal.studentId` or add an `getId()` alias on `UserPrincipal` to avoid evaluation errors.

## Contributing

Please read `CONTRIBUTING.md` for contribution guidelines.

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.
