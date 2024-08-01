package com.backend.backend_firman_ismail_hariri.model.responseHandler;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAPI {
    private Integer code;
    private String message;
    private HttpStatus status;
    private Object data;
}
