package com.example.entity;

import com.demo.entity.ApiResponseObject;
import com.example.dto.ResponseProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse <T extends Object>{

    private int status;
    private String message;
    private T data;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ApiResponse(HttpStatus httpStatus, String message, T data) {
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
    }
}