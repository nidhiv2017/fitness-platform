# Fitness Platform

A full-stack Client–Trainer Fitness Platform (React frontend, Spring Boot backend, PostgreSQL database) with Docker Compose for local development.

This README walks through everything from prerequisites to running the whole stack, step-by-step, for someone new to the project or development.

## Project layout
- `fitness/` — Spring Boot backend (Maven)
- `frontend/` — React + Vite frontend
- `database/` — optional SQL initialization files
- `docker-compose.yml` — development Compose configuration (postgres, backend, frontend)

## Architecture
Client Browser → React Frontend → REST APIs → Spring Boot Backend → JPA → PostgreSQL

---

## Prerequisites
Make sure you have the following installed on your machine (or use GitHub Codespaces which provides most tools):
- Git
- Docker Engine and Docker Compose (or Docker Desktop)
- Node.js (for local frontend dev) — recommended: Node 22.x
- Java 17 (if you will run backend locally)
- (Optional) PostgreSQL client `psql` for DB debugging

If you use Codespaces, Docker and ports can be forwarded via the Codespaces UI.

---

## Quick start (recommended — Docker Compose)
This runs Postgres, builds and runs backend and frontend in containers.

1. Open a terminal at the repository root.

2. Build and start all services:

```bash
docker compose up --build
```

3. Services exposed locally (container → host port mapping):
- Frontend (Vite dev server): http://localhost:5173
- Backend (Spring Boot): http://localhost:8080
- Postgres: 5432 (only needed if you want to connect from host tools)

4. Seed data: the backend contains a startup seed loader that inserts demo trainers, clients and a couple of workouts when the DB is empty. You do not need to run anything else.

5. Open the frontend in your browser: http://localhost:5173

If you are using Codespaces, use the forwarded ports tab to open ports 5173 and 8080 in the browser preview.

---

## Run services individually (local dev)
### Backend (locally, not in Docker)
1. Ensure Java 17 is installed.
2. Configure DB connection. By default the backend uses environment variables or values in `fitness/src/main/resources/application.properties`. For local dev you can:

- Run a local Postgres or use the Docker Postgres from earlier and set these env vars before starting:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/fitnessdb
export SPRING_DATASOURCE_USERNAME=fitness
export SPRING_DATASOURCE_PASSWORD=fitness123
```

3. Build and run backend using the Maven wrapper:

```bash
cd fitness
./mvnw spring-boot:run
```

- Backend runs on: http://localhost:8080
- The seed loader runs automatically if the DB is empty.

### Frontend (locally)
1. Install Node.js 22.x (Vite requires Node >= 20.19 or 22.12+). If you have an older Node, upgrade.
2. Install dependencies:

```bash
cd frontend
npm install
```

3. Start the dev server:

```bash
# If your backend is running on a different host than 'http://localhost:8080', set VITE_BACKEND_URL
export VITE_BACKEND_URL=http://localhost:8080
npm run dev -- --host 0.0.0.0
```

- Frontend dev server listens on port 5173.

Notes about Node version errors
- If you see an error like: "Vite requires Node.js version 20.19+ or 22.12+" — install Node 22+ or use the Dockerfile (already updated to `node:22-alpine`).

---

## How the database is initialized / seeded
- `fitness/src/main/java/com/fitness/fitness/config/SeedDataLoader.java` contains the CommandLineRunner that seeds demo data on startup if the `users` table is empty.
- The Docker Compose setup also mounts `./database` into Postgres initialization folder for `*.sql` scripts if present. Right now `database/init.sql` is present but empty; the application seed loader is the source of demo data.

---

## Important API endpoints (examples)
Base URL: http://localhost:8080

- Authentication
	- POST `/api/auth/login`  — Body: `{ "email": "client@example.com", "password": "client123" }` — returns user id, name, email, role
	- POST `/api/auth/register`  — Body: `{ name, email, password, role }`

- Users
	- GET `/api/users` — list users
	- GET `/api/users/{id}` — get user
	- POST `/api/users` — create user
	- PUT `/api/users/{id}` — update user
	- DELETE `/api/users/{id}` — delete user

- Trainers
	- GET `/api/trainers/{trainerId}/clients` — list clients for trainer and global clients
	- POST `/api/trainers/{trainerId}/clients/{clientId}` — assign client
	- DELETE `/api/trainers/{trainerId}/clients/{clientId}` — remove client

- Workouts
	- POST `/api/workouts` — create workout
	- GET `/api/workouts/client/{clientId}` — list workouts for client
	- PUT `/api/workouts/{id}` — update workout
	- DELETE `/api/workouts/{id}` — delete workout

Example curl (login):

```bash
curl -s -X POST http://localhost:8080/api/auth/login \
	-H 'Content-Type: application/json' \
	-d '{"email":"client@example.com","password":"client123"}'
```

---

## Environment variables
When using Docker Compose, the Compose file sets these env vars for the backend service:
- `SPRING_DATASOURCE_URL` — JDBC URL (defaults to `jdbc:postgresql://postgres:5432/fitnessdb` in Compose)
- `SPRING_DATASOURCE_USERNAME` — DB user (defaults to `fitness`)
- `SPRING_DATASOURCE_PASSWORD` — DB password (defaults to `fitness123`)

For frontend dev server you can set:
- `VITE_BACKEND_URL` — URL of the backend API used by the frontend (default: `http://localhost:8080`)

---

## Troubleshooting
- Vite / Node version error: "Vite requires Node.js version 20.19+ or 22.12+"
	- Use Node 22.x (install or use Docker). The project `frontend/Dockerfile` has been updated to `node:22-alpine` for this reason.

- `ReferenceError: CustomEvent is not defined` inside container
	- This is typically caused by an unsupported Node version for Vite. Use Node 22.

- Backend returns HTTP 401 for `/health` or redirects to `/login`:
	- The backend includes Spring Security. If you see unexpected auth behavior, check `fitness/src/main/resources/application.properties` and `fitness/src/main/java/com/fitness/fitness/config/SecurityConfig.java`. For development the `SecurityConfig` permits `/api/**`.

- Docker Compose port conflicts: ensure ports 5173, 8080, 5432 are free on the host or change the mapping in `docker-compose.yml`.

---

## Development notes (code structure)
Backend (`fitness/`):
- `entity` — JPA entities: `User`, `Workout`, `TrainerClientRoster`
- `repository` — Spring Data JPA interfaces
- `service` — business logic
- `controller` — REST API controllers
- `config` — security, CORS, seed loader

Frontend (`frontend/`):
- `src/pages` — React pages: Login, Register, TrainerDashboard, ClientDashboard, TrainerClientDetail
- `src/api.js` — simple API client used by the pages

---

## Tests
- Backend unit tests: run in `fitness`:

```bash
cd fitness
./mvnw test
```

- Frontend lint and build:

```bash
cd frontend
npm run lint
npm run build
```

---

## Contributing
1. Fork the repository, create a branch, implement changes.
2. Run the full stack locally with Docker Compose and add tests for backend code.
3. Open a pull request with a clear description of your changes.

---

## Contact / Help
If you get stuck, open an issue in the repository describing: OS, steps performed, command outputs, and logs from `docker compose logs` for the services.

---

## License
This repository currently has no license file; add one if you intend to allow reuse.
