package com.backend.backend_firman_ismail_hariri.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignupRequest implements Serializable {
    private String Username;
    private String password;
    private String role;
}
