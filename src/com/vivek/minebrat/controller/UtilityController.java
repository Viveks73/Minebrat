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
public class UtilityController {
    private final PermutationService permutationService;

    @GetMapping("/permutations")
    public ResponseEntity<Set<String>> getPermutations(@RequestParam String input) {
        return ResponseEntity.ok(permutationService.permute(input));
    }
}
