package com.vinicius.productscatalog.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("MinhaChaveJWT123456MinhaChaveJWT123456".getBytes());

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Dados mockados para o desafio
        if ((email.equals("admin@saw.com") && password.equals("admin123")) ||
                (email.equals("user@saw.com") && password.equals("user123"))) {

            String role = email.startsWith("admin") ? "ADMIN" : "USER";

            String token = Jwts.builder()
                    .setSubject(email)
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                    .compact();

            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciais inválidas"));
        }
    }

    public static class LoginRequest {
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O email deve ser válido")
        private String email;

        @NotBlank(message = "A senha é obrigatória")
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;

        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
