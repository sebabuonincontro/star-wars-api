# SWAPI Microservice

Java microservice with Spring Boot that consumes the Star Wars API (SWAPI) implementing hexagonal architecture.

## 🏗️ Architecture

This project follows **Hexagonal Architecture (Ports & Adapters)** principles:

```
src/main/java/com/starwars/swapi/
├── domain/                    # Domain layer (core)
│   ├── model/                # Domain entities
│   ├── port/
│   │   └── output/          # Output ports
│   └── sorting/             # Sorting strategies (Open-Closed)
├── application/              # Application layer
│   └── services/             # Service implementations
└── infrastructure/           # Infrastructure layer
    ├── adapter/              # Adapters (e.g., SWAPI Adapter)
    └── rest/                 # REST controllers
```

## 🚀 Features

- ✅ **Hexagonal Architecture**: Clear separation of concerns
- ✅ **Pagination**: 15 items per page
- ✅ **Search**: By name (case-insensitive, partial matches)
- ✅ **Sorting**: By "name" and "created" (ascending/descending)
- ✅ **Open-Closed Principle**: Extensible sorting system
- ✅ **Docker**: Complete containerization
- ✅ **Tests**: Unit test coverage

## 📋 Requirements

- Git
- Java 21+
- Maven 3.8+
- Docker
- Docker Compose

## 🔧 Installation and Execution

### Option 1: With Maven

```bash
# Clone the repository
git clone <repository-url>
cd star-wars-api

# Build
mvn clean package

# Run
mvn spring-boot:run

Important: The service will be available at http://localhost:8080

```

### Option 2: With Docker Compose

```bash
# Start the service
docker-compose up (or docker-compose up -d for detached mode)

Important: The service will be available at http://localhost:6969

# View logs
docker-compose logs -f

# Stop the service
docker-compose down
```

## 📡 API Endpoints

### Base URL
```
http://localhost:6969/api/v1
```

### 1. Get People

```bash
GET /api/v1/people
```

**Query parameters:**
- `page` (optional): Page number (default: 1)
- `search` (optional): Search by name (case-insensitive)
- `sortBy` (optional): Field to sort by ("name", "created")
- `order` (optional): Order ("asc", "desc") (default: "asc")

**Examples:**

```bash
# Get first page
curl "http://localhost:6969/api/v1/people?page=1"

# Search by name (e.g., "sky" finds "Luke Skywalker")
curl "http://localhost:6969/api/v1/people?search=sky"

# Sort by name descending
curl "http://localhost:6969/api/v1/people?sortBy=name&order=desc"

# Sort by creation date
curl "http://localhost:6969/api/v1/people?sortBy=created&order=asc"

# Combine search and sorting
curl "http://localhost:6969/api/v1/people?search=sky&sortBy=name&order=asc"
```

**Example response:**
```json
{
  "count": 82,
  "next": "2",
  "previous": null,
  "results": [
    {
      "name": "Luke Skywalker",
      "height": "172",
      "mass": "77",
      "hairColor": "blond",
      "skinColor": "fair",
      "eyeColor": "blue",
      "birthYear": "19BBY",
      "gender": "male",
      "created": "2014-12-09T13:50:51.644000",
      "edited": "2014-12-20T21:17:56.891000",
      "url": "https://swapi.dev/api/people/1/"
    }
  ],
  "currentPage": 1,
  "totalPages": 6,
  "pageSize": 15
}
```

### 2. Get Planets

```bash
GET /api/v1/planet
```

**Query parameters:**
- `page` (optional): Page number (default: 1)
- `search` (optional): Search by name
- `sortBy` (optional): Field to sort by ("name", "created")
- `order` (optional): Order ("asc", "desc")

**Examples:**

```bash
# Get first page
curl "http://localhost:6969/api/v1/planet?page=1"

# Search planets
curl "http://localhost:6969/api/v1/planet?search=tatooine"

# Sort by name
curl "http://localhost:6969/api/v1/planet?sortBy=name&order=asc"
```

**Example response:**
```json
{
  "count": 60,
  "next": "2",
  "previous": null,
  "results": [
    {
      "name": "Tatooine",
      "rotationPeriod": "23",
      "orbitalPeriod": "304",
      "diameter": "10465",
      "climate": "arid",
      "gravity": "1 standard",
      "terrain": "desert",
      "surfaceWater": "1",
      "population": "200000",
      "created": "2014-12-09T13:50:49.641000",
      "edited": "2014-12-20T20:58:18.411000",
      "url": "https://swapi.dev/api/planets/1/"
    }
  ],
  "currentPage": 1,
  "totalPages": 4,
  "pageSize": 15
}
```

## 🧪 Testing

### Run Unit Tests

```bash
# Run all tests
mvn clean test

```

### Tests with Postman

Inside of the *postman* folder you can find a postman collection that you can import and use to test the different endpoints and functionalities of the SWAPI microservice.

### Things about improvement

- Include parallelism when fetching data from SWAPI to improve performance, using Virtual Threads.
- Implement caching mechanism to reduce redundant API calls to SWAPI.
- Include security features such as authorization and authentication.
- Add more comprehensive error handling and logging.
- Running unit tests with code coverage reports, using parallel execution to speed up the process.

## 👥 Author

Sebastian Buonincontro.

