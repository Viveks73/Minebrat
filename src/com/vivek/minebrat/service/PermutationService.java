package com.vivek.minebrat.service;

import com.vivek.minebrat.dto.*;
import com.vivek.minebrat.entity.*;
import com.vivek.minebrat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class PermutationService {
    public Set<String> permute(String str) {
        Set<String> result = new HashSet<>();
        permuteHelper("", str, result);
        return result;
    }

    private void permuteHelper(String prefix, String remaining, Set<String> result) {
        if (remaining.isEmpty()) {
            result.add(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            permuteHelper(
                    prefix + remaining.charAt(i),
                    remaining.substring(0, i) + remaining.substring(i + 1),
                    result
            );
        }
    }
}
