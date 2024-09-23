### Note on Curl Installation in Docker Containers

When using Docker Compose without a Dockerfile, you might encounter issues with the absence of `curl` in the container, especially when setting up health checks or performing tests.

#### Problem:
- Attempting to use `curl` in a Docker container results in an error:  
  `exec: "curl": executable file not found in $PATH`.

#### Solution Options:
1. **Using a Dockerfile**: 
   - Create a Dockerfile to extend the existing image and install `curl`:
     ```dockerfile
     FROM tusharnagdivedev/configserver:v2

     # Install curl
     RUN apt-get update && apt-get install -y curl
     ```
   - Build and use this custom image in your `docker-compose.yml`.

2. **Using Docker Compose Only**:
   - Currently, there is no direct way to install additional packages (like `curl`) in an image defined in Docker Compose without a Dockerfile.
   - If using only Docker Compose, consider using another lightweight base image that includes `curl` or run commands directly on your host machine for testing.

3. **Alternative Approach**:
   - If a service in the same Docker network can access the endpoint, use its service name for health checks instead of `curl` directly:
     ```yaml
     healthcheck:
       test: "wget --spider --quiet http://configserver:8000/actuator/health/readiness || exit 1"
     ```

#### Health Check Configuration:
Make sure your health check uses an appropriate test that doesn’t rely on `curl` if you opt to avoid creating a Dockerfile.

#### Summary:
While installing `curl` directly in a container without a Dockerfile is not feasible, you can use alternative methods for testing or modifying your Docker setup accordingly.
