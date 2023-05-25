package com.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String productname;
    Long price;
    Long qty;
    String brand;

    public Product(Long id, String productname, Long price, Long qty, String brand) {
        this.id=id;
        this.productname=productname;
        this.price=price;
        this.qty=qty;
        this.brand=brand;


    }


}
