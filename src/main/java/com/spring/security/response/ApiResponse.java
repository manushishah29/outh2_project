package com.spring.security.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ApiResponse(HttpStatus httpStatus, String message, Object data) {
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message, Object data, int status) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

}
