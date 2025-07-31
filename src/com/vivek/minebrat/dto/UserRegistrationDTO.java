package com.vivek.minebrat.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String email;
    private AddressDTO address;
}

