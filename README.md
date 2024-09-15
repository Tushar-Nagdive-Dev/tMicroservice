# tMicroservice

### For Run RabbitMQ usign docker
`docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management`

application.yml

```
spring:
  rabbitmq:
  host: "localhost"
  port: 5672
  username: "guest"
  password: "guest"
```
Need to add this line `application.yml`


## Webhooks in GitHub: A Brief Overview

**Webhooks** in GitHub are a powerful tool that allows you to trigger custom actions or scripts when specific events occur in your repositories. These events can include anything from pushing new code to creating issues or merging pull requests. When a webhook is triggered, GitHub sends an HTTP POST request to a specified URL, containing information about the event that occurred.

### How Webhooks Work

1. **Create a webhook:** In your GitHub repository, navigate to the **Settings** tab and then select **Webhooks**. Click the **Add webhook** button.
2. **Configure the webhook:**
   * **Payload URL:** Enter the URL of your application where you want to receive the webhook.
   * **Content type:** Choose the content type (e.g., `application/json`) for the payload.
   * **Secret:** Optionally, set a secret to verify the webhook's authenticity.
   * **Events:** Select the events you want to trigger the webhook.
3. **Test the webhook:** GitHub will send a test payload to your specified URL to ensure the webhook is configured correctly.

### Using Webhooks in a Spring Boot Application

**1. Create a Spring Boot application:**
   * Use Spring Initializr to create a new Spring Boot project.
   * Add dependencies for `spring-boot-starter-web` and `com.fasterxml.jackson.core:jackson-databind` to handle HTTP requests and JSON parsing.

**2. Create a controller:**
   * Define a controller class with a method to handle the webhook request.
   * Annotate the method with `@PostMapping` to handle POST requests.
   * Use `@RequestBody` to extract the webhook payload from the request body.

**3. Handle the webhook payload:**
   * Parse the webhook payload and extract the relevant information.
   * Perform the desired actions based on the event that triggered the webhook.

**Example:**

```java
@RestController
public class WebhookController {

    @PostMapping("/webhook")
    public void handleWebhook(@RequestBody WebhookEvent event) {
        // Extract information from the webhook event
        String repositoryName = event.getRepository().getName();
        String action = event.getAction();

        // Perform actions based on the event
        if ("push".equals(action)) {
            // Handle push event
            System.out.println("New code pushed to repository: " + repositoryName);
        } else if ("issue".equals(action)) {
            // Handle issue event
            System.out.println("New issue created in repository: " + repositoryName);
        }
    }
}
```

**Note:**

* The `WebhookEvent` class should be defined to match the structure of the webhook payload you're receiving.
* Consider using a library like `jackson-databind` to deserialize the webhook payload into a POJO.
* Implement appropriate error handling and security measures to protect your application from malicious webhooks.

By following these steps, you can effectively use webhooks to automate tasks and integrate your Spring Boot application with GitHub.


Hookdeck is a powerful tool that allows you to inspect, debug, and replay HTTP requests and responses in your development and production environments. It provides a visual interface for analyzing network traffic, making it easier to identify and troubleshoot issues.

**Key Features:**

* **Real-time request and response inspection:** View incoming and outgoing HTTP requests and responses as they happen.
* **Request replay:** Replay requests to reproduce issues and test different scenarios.
* **Metadata and headers:** Examine detailed metadata and headers for each request.
* **Body inspection:** Inspect the request and response bodies, including JSON, XML, and other formats.
* **Filtering and search:** Filter requests by URL, method, status code, and other criteria.
* **Integration with popular tools:** Integrate with development tools like Postman, Insomnia, and curl.
* **Cloud-based or self-hosted:** Choose between a cloud-based service or self-hosting the Hookdeck server.

**How to Use Hookdeck:**

1. **Install Hookdeck:** Download and install the Hookdeck agent for your operating system.
2. **Start the agent:** Run the agent and configure it to listen on a specific port.
3. **Configure your application:** Modify your application's network settings to route traffic through the Hookdeck agent.
4. **Access the dashboard:** Open the Hookdeck dashboard in your web browser and connect to the agent.
5. **Inspect requests and responses:** Use the dashboard to view, filter, and replay HTTP traffic.

**Example Usage:**

1. **Install the Hookdeck agent:** Download and install the agent for your operating system.
2. **Start the agent:** Run the agent and configure it to listen on port 8080:
   ```bash
   hookdeck-agent --port 8080
   ```
3. **Configure your application:** Modify your application's network settings to route traffic through the Hookdeck agent. For example, in a Node.js application, you can use the `http-proxy-middleware` package:
   ``javascript
   const express = require('express');
   const { createProxyMiddleware } = require('http-proxy-middleware');

   const app = express();

   app.use('/api', createProxyMiddleware({
     target: 'https://your-api-endpoint',
     changeOrigin: true,
     ws: true,
     proxyTimeout: 5000,
     agent: require('https').Agent({
       keepAlive: true
     })
   }));

   app.listen(3000);
   ```
4. **Access the dashboard:** Open the Hookdeck dashboard in your web browser and connect to the agent:
   * Go to https://app.hookdeck.com.
   * Enter the agent's hostname and port (e.g., `localhost:8080`).
5. **Inspect requests and responses:** Use the dashboard to view, filter, and replay HTTP traffic.

**Additional Tips:**

* Explore the Hookdeck dashboard to familiarize yourself with its features.
* Use the search bar to quickly find specific requests.
* Create filters to save common search criteria.
* Consider using Hookdeck's API to automate tasks or integrate it with other tools.

By following these steps and leveraging Hookdeck's features, you can gain valuable insights into your network traffic and streamline your development and troubleshooting process.




