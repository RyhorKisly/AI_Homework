# JSONPlaceholder Clone

A Spring Boot application that clones the functionality of JSONPlaceholder API with added authentication and security features.

## Features

- RESTful API endpoints for user management
- JWT-based authentication
- PostgreSQL database with Flyway migrations
- Docker support for easy deployment
- Gradle build system

## Prerequisites

- Java 21 or later
- Docker and Docker Compose
- Gradle (optional, as the project includes Gradle wrapper)

## Getting Started

### Using Docker (Recommended)

1. Clone the repository:
```bash
git clone <repository-url>
cd jsonplaceholder-clone
```

2. Build and start the containers:
```bash
docker-compose up -d
```

The application will be available at `http://localhost:8080`

### Manual Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd jsonplaceholder-clone
```

2. Build the project using Gradle wrapper:
```bash
./gradlew build
```

3. Run the application:
```bash
./gradlew bootRun
```

## API Endpoints

### Authentication

- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Users

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## Database

The application uses PostgreSQL with Flyway for database migrations. The database schema is automatically created and populated with initial data when the application starts.

### Database Configuration

Database configuration can be found in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://db:5432/jsonplaceholder
    username: postgres
    password: postgres
```

## Security

The application uses JWT (JSON Web Tokens) for authentication. To access protected endpoints, include the JWT token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

## Development

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── jsonplaceholderclone/
│   │               ├── controller/
│   │               ├── model/
│   │               ├── repository/
│   │               ├── security/
│   │               └── service/
│   └── resources/
│       ├── db/
│       │   └── migration/
│       └── application.yml
└── test/
```

### Building

To build the project:

```bash
./gradlew build
```

To run tests:

```bash
./gradlew test
```

### Docker Support

The project includes Docker and Docker Compose configuration for easy deployment:

- `Dockerfile` - Defines the application container
- `docker-compose.yml` - Defines the application and database services

To build and run using Docker:

```bash
docker-compose up -d
```

To stop the containers:

```bash
docker-compose down
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 