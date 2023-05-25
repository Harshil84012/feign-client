package com.example.client;


import com.demo.feign.generated.client.ProductControllerApi;
import com.example.config.FeignConfig;
import com.example.dto.RequestProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(url = "http://localhost:9090/product",
        value = "FeignService",
        configuration = FeignConfig.class)
public interface Client extends ProductControllerApi {

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> addProduct(@RequestBody @Valid RequestProductDto requestProductDto);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> updateProduct(@RequestBody @Valid RequestProductDto requestProductDto, @PathVariable Long id);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<Object> getProductById(@PathVariable("id") Long id);

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id);

    @GetMapping(value = "/display")
    ResponseEntity<Object> getAllProduct();
}
