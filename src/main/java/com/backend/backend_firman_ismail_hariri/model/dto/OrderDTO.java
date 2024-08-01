package com.backend.backend_firman_ismail_hariri.model.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private Long productId;
    private int quantity;
    private double totalPrice;
    private boolean freeShipping;
}
