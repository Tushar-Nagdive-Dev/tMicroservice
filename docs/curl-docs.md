Here's a comprehensive guide to using `curl` for making HTTPS requests:

---

## **`curl` - HTTPS Requests Documentation**

### 1. **Basic HTTPS GET Request**
To make a simple HTTPS GET request, use:
```bash
curl https://example.com
```

### 2. **HTTPS GET Request with Query Parameters**
To include query parameters in a GET request:
```bash
curl "https://example.com/api?param1=value1&param2=value2"
```

### 3. **HTTPS POST Request**
To make an HTTPS POST request with form data:
```bash
curl -X POST https://example.com/api -d "param1=value1&param2=value2"
```

Alternatively, using `--data`:
```bash
curl --data "param1=value1&param2=value2" https://example.com/api
```

### 4. **POST Request with JSON Data**
For sending JSON data, use `-H` to specify the `Content-Type` and `-d` for the payload:
```bash
curl -X POST https://example.com/api \
     -H "Content-Type: application/json" \
     -d '{"key1":"value1", "key2":"value2"}'
```

### 5. **Sending HTTP Headers**
To include custom HTTP headers, use the `-H` option:
```bash
curl -H "Authorization: Bearer <token>" \
     -H "Content-Type: application/json" \
     https://example.com/api
```

### 6. **HTTPS PUT Request**
For a PUT request:
```bash
curl -X PUT https://example.com/api/resource/1 \
     -H "Content-Type: application/json" \
     -d '{"name": "Updated Name"}'
```

### 7. **HTTPS DELETE Request**
To send a DELETE request:
```bash
curl -X DELETE https://example.com/api/resource/1
```

### 8. **Sending Files in POST Requests**
To upload a file in a POST request:
```bash
curl -X POST https://example.com/upload \
     -F "file=@/path/to/file.txt"
```

For multiple files:
```bash
curl -X POST https://example.com/upload \
     -F "file1=@/path/to/file1.txt" \
     -F "file2=@/path/to/file2.txt"
```

### 9. **Follow Redirects**
To automatically follow HTTP redirects, use the `-L` option:
```bash
curl -L https://example.com/redirect
```

### 10. **Verbose Mode**
To see detailed request and response information, use the `-v` option:
```bash
curl -v https://example.com
```

### 11. **Save Response to File**
To save the response body to a file:
```bash
curl https://example.com -o response.html
```

### 12. **Insecure HTTPS Request**
If the server uses a self-signed certificate or you want to skip certificate verification, use the `-k` option (not recommended for production):
```bash
curl -k https://example.com
```

### 13. **Basic Authentication**
To send a request with basic authentication:
```bash
curl -u username:password https://example.com
```

Alternatively, you can manually set the `Authorization` header:
```bash
curl -H "Authorization: Basic <base64_encoded_credentials>" https://example.com
```

### 14. **Passing Cookies**
To send cookies in the request:
```bash
curl -b "cookie1=value1; cookie2=value2" https://example.com
```

To store cookies in a file and reuse them:
```bash
curl -c cookies.txt https://example.com
curl -b cookies.txt https://example.com/next
```

### 15. **Custom Request Method**
You can specify any HTTP method using `-X`:
```bash
curl -X PATCH https://example.com/api/resource/1 \
     -H "Content-Type: application/json" \
     -d '{"name": "Partially Updated Name"}'
```

### 16. **Setting Timeouts**
To set connection and overall request timeouts:
```bash
curl --connect-timeout 10 https://example.com
curl --max-time 20 https://example.com
```

### 17. **Include Response Headers in Output**
To see both the response headers and body:
```bash
curl -i https://example.com
```

If you want only the headers:
```bash
curl -I https://example.com
```

### 18. **HTTP/2 Requests**
To make an HTTP/2 request (assuming the server supports it):
```bash
curl --http2 https://example.com
```

### 19. **Storing Output**
To save the response to a file and also print it to the console:
```bash
curl https://example.com -o output.txt --progress-bar
```

To append to a file instead of overwriting:
```bash
curl https://example.com -o output.txt --progress-bar --create-dirs
```

### 20. **Custom User-Agent**
To set a custom `User-Agent` string:
```bash
curl -A "MyCustomAgent/1.0" https://example.com
```

### 21. **Proxying HTTPS Requests**
To make a request via a proxy:
```bash
curl -x http://proxy.example.com:8080 https://example.com
```

For HTTPS proxy:
```bash
curl -x https://proxy.example.com:8080 https://example.com
```

### 22. **Rate Limiting Requests**
To limit the transfer rate:
```bash
curl --limit-rate 100k https://example.com
```

### 23. **Upload Binary Data**
To send binary data with a POST request:
```bash
curl --data-binary @/path/to/file.bin https://example.com/upload
```

### 24. **Debugging SSL Issues**
To debug SSL/TLS connections:
```bash
curl -v --trace-ascii debug.txt https://example.com
```

### 25. **Custom DNS Resolution**
To override DNS resolution for specific hosts:
```bash
curl --resolve example.com:443:192.168.1.1 https://example.com
```

---

This documentation covers the most commonly used `curl` options for making HTTPS requests. You can refer to the official `curl` man page or [curl documentation](https://curl.se/docs/manpage.html) for further details.

curl -X POST -H "content-Type: application/json" -d '{"name":"account entry.0.1", "email":"accountEntry001@mail.com", "mobileNumber":"8976599467"}' http://localhost:8001/api/create
