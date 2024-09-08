**OpenAPI** is a widely used standard for documenting REST APIs. In Spring Boot, **SpringDoc OpenAPI** is one of the most popular libraries for automatically generating OpenAPI 3.0 documentation and exposing it through a Swagger UI. This provides both a machine-readable API specification and an interactive interface for developers to explore and test the API.

### Detailed Explanation of Documenting Spring Boot REST API using OpenAPI (SpringDoc)

#### 1. **What is OpenAPI?**

The **OpenAPI Specification (OAS)** defines a standard way to describe REST APIs. It specifies the API's endpoints, input/output parameters, authentication methods, request/response types, and more. The goal is to make APIs easily understandable by both humans and machines.

#### 2. **What is SpringDoc OpenAPI?**

**SpringDoc OpenAPI** is a library that generates OpenAPI 3.0 documentation for your Spring Boot application automatically. It scans your codebase for REST controllers and exposes a Swagger UI where developers can interact with the API. Unlike previous versions of Swagger (such as SpringFox), SpringDoc OpenAPI directly supports OpenAPI 3.0, making it the preferred choice for newer Spring Boot projects.

#### 3. **How to Set Up SpringDoc OpenAPI**

##### Step 1: Add SpringDoc Dependencies

In your `pom.xml` file (for Maven projects), add the following dependency for SpringDoc OpenAPI:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.7.0</version> <!-- Update this to the latest version -->
</dependency>
```

If you’re using **Gradle**, add this line to your `build.gradle`:

```groovy
implementation 'org.springdoc:springdoc-openapi-ui:1.7.0'
```

##### Step 2: Start Your Application

After adding the SpringDoc dependency, no additional configuration is required. Once the Spring Boot application starts, OpenAPI automatically generates and exposes the documentation at the following endpoints:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON spec: `http://localhost:8080/v3/api-docs`
- OpenAPI YAML spec: `http://localhost:8080/v3/api-docs.yaml`

You can now view and interact with your API using the Swagger UI interface.

#### 4. **Enhancing API Documentation**

While SpringDoc can auto-generate basic documentation from your REST controllers, you can enhance the documentation using OpenAPI annotations.

##### Example: Simple REST Controller

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class UserController {

    @Operation(summary = "Get a user by their ID", description = "Provide an ID to look up a specific user")
    @GetMapping("/users/{id}")
    public User getUserById(
            @Parameter(description = "ID of the user to be searched") @PathVariable Long id) {
        return new User(id, "John Doe");
    }
}
```

- **@Operation**: Adds metadata to the API operation (e.g., summary, description).
- **@Parameter**: Adds descriptions to the parameters of your API.

##### Example: Enhancing API Response Documentation

You can document the possible API responses using the `@ApiResponse` annotation.

```java
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ProductController {

    @Operation(summary = "Get product details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        // Method logic here
        return new Product(id, "Laptop", "Electronics");
    }
}
```

#### 5. **OpenAPI Annotations Overview**

Here are some commonly used OpenAPI annotations to enhance your Spring Boot API documentation:

- **@Operation**: Describes a method (operation) in the API.
  - `summary`: Short description of the operation.
  - `description`: Detailed explanation of the operation.
- **@Parameter**: Describes a method parameter.
  - `description`: Adds details to the parameter.
- **@ApiResponse**: Describes a response from an API operation.
  - `responseCode`: The HTTP status code.
  - `description`: Explanation of what the response means.
- **@RequestBody**: Describes the content of the request body.
- **@Schema**: Describes a model/schema for request/response data.
- **@Tag**: Adds tags to group related API operations.
- **@SecurityRequirement**: Describes security schemes (e.g., API keys, OAuth2).

#### 6. **Customizing OpenAPI Info**

You can customize the title, version, description, and other metadata for your API using the `@OpenAPIDefinition` annotation.

Here’s an example:

```java
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Product API",
        version = "1.0",
        description = "API for managing products",
        contact = @Contact(name = "API Support", email = "support@example.com")
    )
)
public class OpenApiConfig {
}
```

This will add metadata to your OpenAPI spec that can be viewed on the Swagger UI page.

#### 7. **Securing API with OpenAPI**

If your API requires authentication, you can document that using the `@SecurityRequirement` annotation.

Here’s how to document a JWT-based authentication mechanism:

```java
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class SecureController {

    @Operation(summary = "Secure endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/secure-data")
    public String getSecureData() {
        return "This is a secure endpoint!";
    }
}
```

In the configuration class, add the security scheme definition:

```java
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Secure API", version = "1.0"))
@SecuritySchemes({
    @SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
})
public class OpenApiSecurityConfig {
}
```

Now, the Swagger UI will include the option to enter a Bearer Token for testing the secure endpoints.

#### 8. **Generating OpenAPI Spec in JSON/YAML**

To generate the OpenAPI spec in JSON or YAML format (useful for integrating with API Gateway tools or other services), SpringDoc provides the following endpoints:

- **JSON**: `http://localhost:8080/v3/api-docs`
- **YAML**: `http://localhost:8080/v3/api-docs.yaml`

These links provide the entire OpenAPI documentation in JSON or YAML format, which can be imported into tools like Postman, SwaggerHub, or used in CI/CD pipelines for testing.

#### 9. **Adding Tags for Grouping APIs**

You can organize your API into logical groups using `@Tag` annotation.

Example:

```java
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User Management", description = "Operations pertaining to users")
public class UserController {

    @Operation(summary = "Get a user by ID")
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return new User(id, "John Doe");
    }
}
```

This will create a "User Management" section in the Swagger UI, making it easier for users to navigate through the documentation.

#### 10. **Using SpringDoc for Versioned APIs**

If you have multiple versions of your API, you can generate separate OpenAPI specifications for each version by configuring SpringDoc to recognize the version path.

Example:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket v1Api() {
        return new Docket(DocumentationType.OAS_30)
            .groupName("v1")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.v1"))
            .build();
    }

    @Bean
    public Docket v2Api() {
        return new Docket(DocumentationType.OAS_30)
            .groupName("v2")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.v2"))
            .build();
    }
}
```

### Summary

1. **SpringDoc OpenAPI** is the preferred way to document Spring Boot REST APIs using the OpenAPI 3.0 specification.
2. **Swagger UI** provides an interactive way to explore and test your API.
3. \*\*Open

http://localhost:8001/swagger-ui/index.html#/
