package com.vivek.minebrat.controller;

import com.vivek.minebrat.dto.*;
import com.vivek.minebrat.entity.User;
import com.vivek.minebrat.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO dto) {
        userService.register(dto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
        boolean isValid = userService.authenticate(dto.getUsername(), dto.getPassword());
        return isValid ? ResponseEntity.ok("Login successful") :
                ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> search(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> pin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> endDate,
            Pageable pageable) {

        return ResponseEntity.ok(userService.search(name, pin, startDate, endDate, pageable));
    }
}

