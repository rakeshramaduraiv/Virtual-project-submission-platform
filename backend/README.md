# Virtual Science Fair Platform - Backend

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL (optional - H2 in-memory database is configured by default)

### Quick Start

1. **Set JAVA_HOME** (if not already set):
   ```cmd
   set JAVA_HOME=C:\Program Files\Java\jdk-17
   set PATH=%JAVA_HOME%\bin;%PATH%
   ```

2. **Build and Run**:
   ```cmd
   mvn clean install
   mvn spring-boot:run
   ```

3. **Alternative - Run JAR directly**:
   ```cmd
   mvn clean package -DskipTests
   java -jar target\springapp-0.0.1-SNAPSHOT.jar
   ```

### API Endpoints

- **Health Check**: `GET /api/test/health`
- **Authentication**: 
  - `POST /api/auth/register`
  - `POST /api/auth/login`
  - `POST /api/auth/logout`
- **Projects**: `GET|POST|PUT|DELETE /api/projects`
- **Users**: `GET|PUT /api/users/profile`
- **Categories**: `GET|POST|PUT|DELETE /api/categories`
- **Evaluations**: `GET|POST|PUT /api/evaluations`
- **Admin**: `GET /api/admin/analytics`, `/api/admin/reports`

### Database

- **Development**: H2 in-memory database (default)
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (empty)

### Configuration

Edit `src/main/resources/application.properties` to:
- Switch to MySQL database
- Change JWT secret and expiration
- Modify server port

### Features Implemented

✅ JWT Authentication with refresh tokens
✅ Role-based security (Admin, Fair Coordinator, Judge, Teacher, Student)
✅ Complete CRUD operations for all entities
✅ Input validation and custom exceptions
✅ CORS configuration for frontend integration
✅ H2 database with auto-schema generation
✅ RESTful API design
✅ Audit logging capability
✅ Notification system

### Troubleshooting

If Maven fails with JAVA_HOME error:
1. Find your Java installation: `where java`
2. Set JAVA_HOME to the JDK directory (not bin)
3. Ensure PATH includes %JAVA_HOME%\bin

The application is ready for frontend integration and production deployment.