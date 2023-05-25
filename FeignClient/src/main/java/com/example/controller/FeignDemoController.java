package com.example.controller;


import com.example.dto.RequestProductDto;
import com.example.dto.ResponseProductDto;
import com.example.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/feignCall")
public class FeignDemoController {

    private final ProductServiceImpl productService;

    @PostMapping(value = "/add")
    public ResponseProductDto addProduct(@RequestBody @Valid RequestProductDto requestProductDto) {
        ResponseProductDto responseProductDto = this.productService.addProduct(requestProductDto);
        return responseProductDto;
    }

    @PutMapping(value = "/update/{id}")
    ResponseProductDto update(@RequestBody @Valid RequestProductDto requestProductDto, @PathVariable Long id) {
        ResponseProductDto responseProductDto = this.productService.update(requestProductDto, id);
        return responseProductDto;
    }

    @GetMapping(value = "/findById/{id}")
    ResponseProductDto findById(@PathVariable Long id) {
        ResponseProductDto responseProductDto = this.productService.findById(id);
        return responseProductDto;
    }

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable long id) {
        this.productService.delete(id);
    }

    @GetMapping(value = "/display")
    ResponseProductDto findAll() {
        ResponseProductDto findAll = this.productService.findAll();
        return findAll;
    }
}
