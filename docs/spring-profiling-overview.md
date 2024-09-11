Spring Profiles in Spring Boot allow you to define different configurations for different environments (e.g., `dev`, `qa`, `prod`). Below is a step-by-step guide on how to implement and use Spring Profiles in a Spring Boot application:

### 1. **Create Profile-Specific Property Files**
Spring Boot uses `application.properties` or `application.yml` by default, but you can define profile-specific configuration files like `application-dev.properties`, `application-prod.properties`, etc.

#### Step 1: Create Property Files

- **application.properties**: Default configuration used if no profile is active.
- **application-dev.properties**: For development environment.
- **application-prod.properties**: For production environment.

Your `src/main/resources` folder should look like this:

```
src/main/resources/
  └── application.properties
  └── application-dev.properties
  └── application-prod.properties
```

#### Example: `application-dev.properties` (Development)
```properties
server.port=8081
spring.datasource.url=jdbc:h2:mem:devdb
spring.datasource.username=sa
spring.datasource.password=password
```

#### Example: `application-prod.properties` (Production)
```properties
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5432/proddb
spring.datasource.username=produser
spring.datasource.password=prodpassword
```

### 2. **Activate a Profile**

You can activate a specific profile in different ways:

#### 2.1 **Activate Profile via `application.properties`**

In your `application.properties`, you can specify the active profile:
```properties
spring.profiles.active=dev
```

#### 2.2 **Activate Profile via Command Line**

You can pass the profile when starting the application:
```bash
java -jar target/yourapp.jar --spring.profiles.active=prod
```

#### 2.3 **Activate Profile via Environment Variable**

You can set the `SPRING_PROFILES_ACTIVE` environment variable to specify the active profile:
```bash
export SPRING_PROFILES_ACTIVE=dev
```

Then, start your application:
```bash
java -jar target/yourapp.jar
```

### 3. **Use Profile-Specific Beans**

You can also configure beans to be loaded based on the active profile by using the `@Profile` annotation in your Spring configuration classes.

#### Example: Profile-Specific Beans
```java
@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:h2:mem:devdb")
            .username("sa")
            .password("password")
            .build();
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://localhost:5432/proddb")
            .username("produser")
            .password("prodpassword")
            .build();
    }
}
```

In this example:
- The `devDataSource` bean is loaded when the `dev` profile is active.
- The `prodDataSource` bean is loaded when the `prod` profile is active.

### 4. **Logging the Active Profile**

To check which profile is active during application startup, you can log the active profile by adding this to your `application.properties`:

```properties
logging.level.org.springframework.boot.context.config=DEBUG
```

This will print detailed information about which profile is being activated.

### 5. **Profile-Specific Configuration in Code**

You can inject the active profile into your classes using the `@Value` annotation or the `Environment` class.

#### Using `@Value` Annotation:
```java
@RestController
public class ProfileController {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping("/profile")
    public String getProfile() {
        return "Active profile: " + activeProfile;
    }
}
```

#### Using `Environment` Class:
```java
import org.springframework.core.env.Environment;

@RestController
public class ProfileController {

    @Autowired
    private Environment env;

    @GetMapping("/profile")
    public String getProfile() {
        return "Active profile: " + env.getActiveProfiles()[0];
    }
}
```

### 6. **Test the Profiles**

Run your application with different profiles to test:

#### For Development Profile:
```bash
java -jar target/yourapp.jar --spring.profiles.active=dev
```

#### For Production Profile:
```bash
java -jar target/yourapp.jar --spring.profiles.active=prod
```

### 7. **Fallback Mechanism**

If no profile-specific configuration is found, Spring Boot will fall back to the default configuration in `application.properties`. However, if you want a certain profile to act as a fallback, you can set:

```properties
spring.profiles.default=dev
```

This will activate the `dev` profile if no other profile is specified.

### Summary:

1. **Create Profile-Specific Property Files** (`application-dev.properties`, `application-prod.properties`).
2. **Activate Profile** using `spring.profiles.active` in `application.properties`, command line, or environment variables.
3. **Configure Beans** for specific profiles using `@Profile`.
4. **Test** by running your application with different profiles.

This is how you can set up and work with profiles in a Spring Boot application step-by-step.
