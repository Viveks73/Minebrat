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
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(UserRegistrationDTO dto) {
        Address address = new Address(null,
                dto.getAddress().getStreet(),
                dto.getAddress().getCity(),
                dto.getAddress().getState(),
                dto.getAddress().getPinCode(),
                LocalDate.now());

        User user = new User(null,
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                LocalDate.now(),
                address);

        userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public Page<User> search(Optional<String> name, Optional<String> pin, Optional<LocalDate> startDate,
                             Optional<LocalDate> endDate, Pageable pageable) {
        List<User> users = userRepository.findAll();

        if (name.isPresent())
            users.retainAll(userRepository.findByUsernameContainingIgnoreCase(name.get()));
        if (pin.isPresent())
            users.retainAll(userRepository.findByAddress_PinCode(pin.get()));
        if (startDate.isPresent() && endDate.isPresent())
            users.retainAll(userRepository.findByRegistrationDateBetween(startDate.get(), endDate.get()));

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), users.size());
        return new PageImpl<>(users.subList(start, end), pageable, users.size());
    }
}