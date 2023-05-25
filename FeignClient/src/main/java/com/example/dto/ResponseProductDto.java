package com.example.dto;

import lombok.*;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseProductDto {

    private String message;
    private Object data;
    private Integer status;


}