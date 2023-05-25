package com.demo.dto;

import lombok.*;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseProductDto {

    Long id;
    String productname;
    Long price;
    Long qty;
    String brand;



}