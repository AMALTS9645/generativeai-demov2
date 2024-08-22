 ```java
// code-start
package com.example.loginapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebResponseException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Register user
            // Ensure to validate user input to prevent injection attacks
            if (user.getUsername() == null || user.getPassword() == null) {
                return ResponseEntity.badRequest().body("Username and password cannot be empty");
            }
            // Add code to save user in database
            // ...
            return ResponseEntity.created().body("User registered successfully");
        } catch (Exception e) {
            // Logging the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Validate username and password
            if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                return ResponseEntity.badRequest().body("Username and password cannot be empty");
            }

            // Add code to check username and password in database
            // ...

            if (validUser) {
                // Generate and return JWT token
                // ...
                return ResponseEntity.ok().body("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            // Logging the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging in");
        }
    }
}
// code-end
```