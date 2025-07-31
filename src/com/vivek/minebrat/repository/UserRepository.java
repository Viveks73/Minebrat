package com.vivek.minebrat.repository;

import com.vivek.minebrat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContainingIgnoreCase(String name);
    List<User> findByAddress_PinCode(String pinCode);
    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);
    User findByUsername(String username);
}