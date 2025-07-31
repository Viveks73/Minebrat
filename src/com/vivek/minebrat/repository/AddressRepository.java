package com.vivek.minebrat.repository;


import com.vivek.minebrat.dto.*;
import com.vivek.minebrat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<com.vivek.minebrat.entity.Address, Long> {
}