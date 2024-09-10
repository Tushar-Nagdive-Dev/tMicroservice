Here's how you can create a Docker image using **Google Jib** and integrate it with **Docker Compose** for your Spring Boot application (`cardswithpostgresql`) with PostgreSQL.

### Step 1: Add Jib to Your Project

For a Maven-based project, add the Jib plugin to your `pom.xml`:

#### `pom.xml` Configuration:

Add this plugin section to the `pom.xml`:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.google.cloud.tools</groupId>
            <artifactId>jib-maven-plugin</artifactId>
            <version>3.3.2</version>
            <configuration>
                <from>
                    <image>eclipse-temurin:22-jdk</image>
                </from>
                <to>
                    <image>cardswithpostgresql-app</image>
                </to>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Step 2: Update `application.yml`

Since you'll be using Docker Compose and PostgreSQL will run in a separate container, update your `application.yml` to reference the PostgreSQL service name (`db`) instead of `localhost`:

#### Updated `application.yml`:

```yaml
server:
  port: 8002

spring:
  application:
    name: cardswithpostgresql

  datasource:
    url: jdbc:postgresql://db:5432/springdb
    username: tusharnagdive
    password: root

  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update

  liquibase:
    change-log: classpath:/db/changelog/db.changelog-master.sql
    enabled: true
```

### Step 3: Create `docker-compose.yml`

Now create a `docker-compose.yml` file to define services for both the Spring Boot application and PostgreSQL.

#### `docker-compose.yml`:

```yaml
version: "3.8"

services:
  app:
    image: cardswithpostgresql-app
    build:
      context: .
    ports:
      - "8002:8002"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/springdb
      SPRING_DATASOURCE_USERNAME: tusharnagdive
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - db

  db:
    image: postgres:13
    environment:
      POSTGRES_DB: springdb
      POSTGRES_USER: tusharnagdive
      POSTGRES_PASSWORD: root
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

### Step 4: Build the Docker Image with Jib

Run the following Maven command to build the Docker image for your Spring Boot app using Jib:

```bash
mvn jib:dockerBuild
```

This will create a Docker image named `cardswithpostgresql-app` and place it in your local Docker repository.

### Step 5: Run the Application with Docker Compose

Once the image is built, you can use Docker Compose to start both the Spring Boot application and PostgreSQL database:

```bash
docker-compose up
```

This will:

- Start the `db` service (PostgreSQL).
- Start the `app` service (your Spring Boot app) after PostgreSQL is ready.

### Step 6: Verify

Visit `http://localhost:8002` to check if your Spring Boot application is running and connected to the PostgreSQL database.

### Additional Notes:

- **Ports**: The app will run on port `8002` as defined in your `application.yml`.
- **Database**: The PostgreSQL database will be running on `localhost:5432` inside the Docker Compose network.

With this setup, you now have a Docker image built by Google Jib and managed through Docker Compose, ensuring that your Spring Boot app and PostgreSQL run together seamlessly.

Let me know if you face any issues!
