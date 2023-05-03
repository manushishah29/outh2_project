package com.spring.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorDTO {
    private Long timestamp;
    private int status;
    private Object error;
    private String message;
    private String path;
    @JsonIgnore
    private HttpStatus httpStatus;
    public ErrorDTO(HttpStatus httpStatus, Long timestamp, String message, String path) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.message = message;
        this.path = path;
    }

    public ErrorDTO(HttpStatus httpStatus, Long timestamp, String message, String path, List<String> error) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.error = error;
        this.message = message;
        this.path = path;
    }
}