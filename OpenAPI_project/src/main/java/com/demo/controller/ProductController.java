package com.demo.controller;


import com.demo.client.CustomProductDetailService;
import com.demo.dto.RequestProductDto;
import com.demo.dto.ResponseProductDto;
import com.demo.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/product")
@Slf4j
@Validated
public class ProductController {

    @Autowired
    CustomProductDetailService customProductDetailService;


    //    private final String API_KEY = "was5sd3d8o9e70cxwsmuhmmoi2g9jte";


    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody RequestProductDto requestProductDto, @RequestHeader(name = "x-api-key", required = true) String headerPersist) {
        System.out.println("headerPersist = " + headerPersist);
//        if (headerPersist.equals(API_KEY)) {
        ResponseProductDto responseProductDto = this.customProductDetailService.save(requestProductDto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product Added Successfully", responseProductDto), HttpStatus.OK);
//        } else {

//        }
    }


    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid RequestProductDto requestProductDto, @PathVariable Long id) {


        ResponseProductDto responseProductDto = this.customProductDetailService.update(requestProductDto, id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product updated Successfully", responseProductDto),
                HttpStatus.OK);

    }


    @GetMapping(value = "/display", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ApiResponse> findAll() {
        List<ResponseProductDto> ls = this.customProductDetailService.findAll();
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product Fetched Successfully", ls), HttpStatus.OK);
    }


    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> findById(@PathVariable long id) {
        ResponseProductDto responseProductDto = this.customProductDetailService.findById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product Fetched id Successfully", responseProductDto), HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable long id) {
        this.customProductDetailService.delete(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product Deleted successfully", Collections.emptyMap()), HttpStatus.OK);
    }


}



