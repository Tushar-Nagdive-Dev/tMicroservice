OAuth 2.0 is an industry-standard protocol designed for authorization, allowing applications to obtain limited access to user accounts on HTTP services. It enables third-party applications to access protected resources on behalf of users without sharing their credentials. Below is a detailed overview of OAuth 2.0, including its roles, grant types, flows, and essential code snippets.

## Key Concepts

### Roles in OAuth 2.0

1. **Resource Owner**: The entity (usually the end-user) that can grant access to the protected resources.
2. **Resource Server**: The server hosting the protected resources (e.g., an API).
3. **Client**: The application requesting access to the resource server on behalf of the resource owner.
4. **Authorization Server**: The server that authenticates the resource owner and issues access tokens after obtaining the necessary authorization.

### Access Tokens

An **Access Token** is a credential used by the client to access protected resources. It represents the authorization granted to the client and may include scopes and expiration information. Access tokens can be in various formats, with JSON Web Tokens (JWT) being common due to their ability to carry claims.

## Grant Types

OAuth 2.0 defines several grant types, each suited for different scenarios:

1. **Authorization Code Grant**: Used by web applications where the client can maintain confidentiality.
2. **Implicit Grant**: Used by public clients (e.g., single-page applications) where the client secret cannot be kept confidential.
3. **Resource Owner Password Credentials Grant**: Used in trusted applications where users provide their credentials directly.
4. **Client Credentials Grant**: Used for machine-to-machine communication where no user is involved.

## Authorization Code Flow

This flow is commonly used for web applications:

1. **Authorization Request**: The client redirects the user to the authorization server to request authorization.

   ```http
   GET /authorize?response_type=code&client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&scope=SCOPE HTTP/1.1
   Host: authorization-server.com
   ```

2. **Authorization Grant**: After user consent, the authorization server redirects back with an authorization code.

   ```http
   GET REDIRECT_URI?code=AUTHORIZATION_CODE
   ```

3. **Token Request**: The client exchanges the authorization code for an access token.

   ```http
   POST /token HTTP/1.1
   Host: authorization-server.com
   Content-Type: application/x-www-form-urlencoded

   grant_type=authorization_code&
   code=AUTHORIZATION_CODE&
   redirect_uri=REDIRECT_URI&
   client_id=CLIENT_ID&
   client_secret=CLIENT_SECRET
   ```

4. **Access Protected Resource**: Use the access token to request resources from the resource server.
   ```http
   GET /resource HTTP/1.1
   Host: resource-server.com
   Authorization: Bearer ACCESS_TOKEN
   ```

### Example Code Snippet for Authorization Code Flow

Here's a Python example using `requests` library:

```python
import requests

# Step 1: Redirect user for authorization (handled in web app)
authorization_url = "https://authorization-server.com/authorize"
params = {
    'response_type': 'code',
    'client_id': 'CLIENT_ID',
    'redirect_uri': 'REDIRECT_URI',
    'scope': 'SCOPE'
}
print(f"Go to this URL to authorize: {authorization_url}?{requests.compat.urlencode(params)}")

# Step 2: Exchange authorization code for access token
token_url = "https://authorization-server.com/token"
data = {
    'grant_type': 'authorization_code',
    'code': 'AUTHORIZATION_CODE',
    'redirect_uri': 'REDIRECT_URI',
    'client_id': 'CLIENT_ID',
    'client_secret': 'CLIENT_SECRET'
}
response = requests.post(token_url, data=data)
access_token = response.json().get('access_token')

# Step 3: Access protected resource
resource_url = "https://resource-server.com/resource"
headers = {'Authorization': f'Bearer {access_token}'}
resource_response = requests.get(resource_url, headers=headers)
print(resource_response.json())
```

## Client Credentials Flow

This flow is used for server-to-server communication without user interaction:

1. **Token Request**:

   ```http
   POST /token HTTP/1.1
   Host: authorization-server.com
   Content-Type: application/x-www-form-urlencoded

   grant_type=client_credentials&
   client_id=CLIENT_ID&
   client_secret=CLIENT_SECRET
   ```

2. **Access Protected Resource**:
   Similar to the previous flow, use the obtained access token.

### Example Code Snippet for Client Credentials Flow

```python
# Token request using Client Credentials flow
client_credentials_url = "https://authorization-server.com/token"
data = {
    'grant_type': 'client_credentials',
    'client_id': 'CLIENT_ID',
    'client_secret': 'CLIENT_SECRET'
}
response = requests.post(client_credentials_url, data=data)
access_token = response.json().get('access_token')

# Access protected resource with obtained token
resource_url = "https://resource-server.com/resource"
headers = {'Authorization': f'Bearer {access_token}'}
resource_response = requests.get(resource_url, headers=headers)
print(resource_response.json())
```

## Conclusion

OAuth 2.0 provides a robust framework for securing APIs and managing delegated access across various applications and devices. By understanding its roles, grant types, and flows, developers can effectively implement secure authentication and authorization mechanisms in their applications.

For further details on OAuth 2.0 specifications and best practices, refer to [RFC 6749](https://datatracker.ietf.org/doc/html/rfc6749) and other official documentation sources like [Auth0](https://auth0.com/docs/authenticate/protocols/oauth) and [DigitalOcean](https://www.digitalocean.com/community/tutorials/an-introduction-to-oauth-2).

Citations:
[1] https://developer.orange.com/resources/oauth-2-0-protocol/
[2] https://auth0.com/intro-to-iam/what-is-oauth-2
[3] https://auth0.com/docs/authenticate/protocols/oauth
[4] https://www.digitalocean.com/community/tutorials/an-introduction-to-oauth-2
[5] https://datatracker.ietf.org/doc/html/rfc6749
[6] https://oauth.net/2/
[7] https://developers.google.com/identity/protocols/oauth2
[8] https://www.keycloak.org/docs/latest/server_admin/
