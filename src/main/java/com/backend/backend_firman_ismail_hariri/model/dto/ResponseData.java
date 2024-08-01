package com.backend.backend_firman_ismail_hariri.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ResponseData {

    private boolean status;

    private List<String> message = new ArrayList<>();

    private Object payload;

}
