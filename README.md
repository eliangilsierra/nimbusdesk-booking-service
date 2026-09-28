# nimbusdesk-booking-service

Core business service of the [NimbusDesk](https://github.com/eliangilsierra/nimbusdesk-infra) system: room booking, availability checks and pricing.

Built **hexagonal** (ports & adapters): `domain` (model, `api`/`spi` ports, use case, pure pricing logic) → `application` (DTOs, mapper, handler) → `infraestructure` (REST input, JPA output, Feign output). `domain` has zero framework imports — see `domain/useCase/BookingUseCase.java` and `domain/util/PricingCalculator.java`.

Before confirming a booking, it checks local availability (MySQL) **and** calls [`nimbusdesk-partner-bridge-service`](https://github.com/eliangilsierra/nimbusdesk-partner-bridge-service) synchronously via Feign — the domain only sees a `PartnerAvailabilityPort`, never the HTTP call itself.

## Stack

- Java 17 · Spring Boot 3.2.4 · Spring Cloud 2023.0.1 (Eureka client, OpenFeign)
- Spring Data JPA + MySQL
- MapStruct · springdoc-openapi

## Endpoints

| Method | Path | Description |
|---|---|---|
| `POST` | `/bookings` | Create a booking (validates local + partner availability, prices it) |
| `GET` | `/bookings/{id}` | Get a booking |
| `DELETE` | `/bookings/{id}` | Cancel a booking |
| `GET` | `/bookings/availability?roomId=&start=&end=` | Check availability |

OpenAPI UI: `/swagger-ui.html`.

Sample rooms (`Aurora`, `Borealis`, `Comet`) are seeded on startup via `data.sql`.

## Configuration

| Env var | Default | Purpose |
|---|---|---|
| `DB_URL` / `DB_USERNAME` / `DB_PASSWORD` | local MySQL, `nimbusdesk_dev` | Database connection |
| `EUREKA_URI` | `http://localhost:8761/eureka/` | Discovery server (also used to resolve `nimbusdesk-partner-bridge-service`) |

## Run locally

Requires MySQL, `nimbusdesk-discovery-server` and (for the availability check to succeed) `nimbusdesk-partner-bridge-service` running — see `nimbusdesk-infra`'s `docker-compose.yml`.

```bash
mvn spring-boot:run
```

Or with Docker:

```bash
docker build -t nimbusdesk-booking-service .
docker run -p 8082:8082 \
  -e DB_URL=jdbc:mysql://host.docker.internal:3306/nimbusdesk \
  -e EUREKA_URI=http://host.docker.internal:8761/eureka/ \
  nimbusdesk-booking-service
```

## How it fits in

Part of the NimbusDesk v1 system. See [`nimbusdesk-infra`](https://github.com/eliangilsierra/nimbusdesk-infra) for the full architecture, ADRs and roadmap.
