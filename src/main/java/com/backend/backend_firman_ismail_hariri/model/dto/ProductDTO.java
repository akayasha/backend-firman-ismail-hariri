package com.backend.backend_firman_ismail_hariri.model.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
}
