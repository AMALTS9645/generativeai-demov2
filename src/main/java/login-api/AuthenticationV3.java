 ```java
package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/")
    public ResponseEntity<?> login(LoginDto loginDto) {
        Optional<User> user = userService.findByUsername(loginDto.getUsername());
        if (user.isPresent()) {
            if (passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {
                return ResponseEntity.ok().body(user.get());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    // UserService interface should be implemented to handle user data retrieval and other operations.
    // Ensure proper security practices by not exposing sensitive user data in the response.
}

package com.example.loginapi.dto;

import java.util.Optional;

public class LoginDto {
    private String username;
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // UserDataObject can be used to carry the necessary user data in the response.
    // Ensure that no sensitive data like passwords or tokens are included in the response.
}
```