Keycloak can be easily deployed using Docker, which simplifies the setup and management of the Keycloak server. Below is a detailed overview of how to run Keycloak in a Docker container, including essential commands and configurations.

## Getting Started with Keycloak in Docker

### Prerequisites

1. **Docker Installed**: Ensure that Docker is installed on your machine.
2. **Docker Hub Account**: Optional, but useful for pulling images.

### Pulling the Keycloak Docker Image

To start, you need to pull the latest Keycloak image from the Docker repository:

```bash
docker pull quay.io/keycloak/keycloak:latest
```

### Running Keycloak

You can run Keycloak in development mode using the following command:

```bash
docker run -p 7080:8080 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:latest start-dev
```

- **-p 8080:8080**: Maps port 8080 on your local machine to port 8080 in the container.
- **KEYCLOAK_ADMIN**: Sets the username for the admin account.
- **KEYCLOAK_ADMIN_PASSWORD**: Sets the password for the admin account.

After running this command, you can access the Keycloak Admin Console at `http://localhost:8080`.

### Advanced Configuration

For more complex setups, such as using a PostgreSQL database or configuring HTTPS, you can use additional environment variables and parameters. Here’s an example of a more advanced command:

```bash
docker run --name keycloak_server -p 8180:8180 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=password \
  quay.io/keycloak/keycloak:latest start \
  --auto-build \
  --db=postgres \
  --db-url=<JDBC-URL> \
  --db-username=<DB-USER> \
  --db-password=<DB-PASSWORD> \
  --https-key-store-file=<file> \
  --https-key-store-password=<password> \
  --http-port=8180
```

### Using Docker Compose

You can also set up Keycloak using Docker Compose for easier management. Here’s an example `docker-compose.yml` file:

```yaml
version: "3"
services:
  auth:
    image: quay.io/keycloak/keycloak:latest
    ports:
      - "8180:8180"
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: password
    command:
      - start-dev
      - --http-port=8180
```

To start Keycloak with this configuration, run:

```bash
docker-compose up
```

### Creating Realms and Users

Once Keycloak is running, you can create realms and manage users through the Admin Console. Access it via `http://localhost:8080/auth/admin` and log in with the admin credentials you set earlier.

1. **Create a Realm**:

   - Click on "Master" in the top left corner and select "Add Realm".
   - Fill in the realm name and click "Create".

2. **Create Users**:
   - Select your realm from the dropdown.
   - Go to "Users" > "Add User" and fill in user details.

### Conclusion

Running Keycloak in Docker simplifies deployment and management, allowing for quick setup and easy configuration changes. Whether for development or production, using Docker provides a flexible environment to manage identity and access management effectively.

For more detailed instructions and advanced configurations, refer to the official [Keycloak documentation](https://www.keycloak.org/getting-started/getting-started-docker) [1][2].

Citations:
[1] https://www.keycloak.org/getting-started/getting-started-docker
[2] https://howtodoinjava.com/devops/keycloak-on-docker/
[3] https://www.keycloak.org/server/containers
[4] https://www.keycloak.org/docs/latest/server_admin/
[5] https://www.digitalocean.com/community/tutorials/an-introduction-to-oauth-2
[6] https://www.simpleorientedarchitecture.com/openid-connect-in-a-nutshell/
[7] https://auth0.com/docs/authenticate/protocols/oauth
[8] https://developer.okta.com/docs/concepts/oauth-openid/
