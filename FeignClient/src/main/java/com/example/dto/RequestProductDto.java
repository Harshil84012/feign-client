package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RequestProductDto {
    @NotEmpty(message = "productname should not be empty")
    String productname;

    @NotNull(message = "price should not be null")
    Long price;

    @NotNull(message = "qty should not be null")
    Long qty;
    @NotEmpty(message = "brand should not be null")
    String brand;

}
