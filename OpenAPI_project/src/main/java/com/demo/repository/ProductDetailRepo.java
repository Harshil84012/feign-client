package com.demo.repository;

import com.demo.dto.ResponseProductDto;
import com.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepo extends JpaRepository<Product,Long> {

//    @Query("SELECT new com.demo.dto.ResponseProductDto(a.productname) from Product a WHERE a.productname=:productname ")
//    List<ResponseProductDto> findByProductName(String productname);
}
