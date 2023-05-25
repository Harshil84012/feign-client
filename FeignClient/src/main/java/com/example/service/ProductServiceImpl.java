package com.example.service;

import com.example.client.Client;
import com.example.dto.RequestProductDto;
import com.example.dto.ResponseProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

    private final Client client;


    public ResponseProductDto addProduct(RequestProductDto requestProductDto) {

        ResponseProductDto responseProductDto = new ResponseProductDto();
        try {
            ResponseEntity<Object> response = client.addProduct(requestProductDto);
            System.out.println("response.getBody() = " + response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.convertValue(response.getBody(), Object.class);
//            System.out.println("jsonObject = " + jsonObject);

            responseProductDto.setStatus(200);
            responseProductDto.setData(jsonObject);
            responseProductDto.setMessage("Product  Added  Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseProductDto;
    }

    public ResponseProductDto update(RequestProductDto requestProductDto, Long id) {

        ResponseProductDto responseProductDto = new ResponseProductDto();
        try {
            ResponseEntity<Object> response = client.updateProduct(requestProductDto, id);
            System.out.println("response.getBody() = " + response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.convertValue(response.getBody(), Object.class);

            responseProductDto.setStatus(200);
            responseProductDto.setData(jsonObject);
            responseProductDto.setMessage("Product  Updated  Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseProductDto;
    }

    public ResponseProductDto findById(Long id) {
        ResponseProductDto responseProductDto = new ResponseProductDto();
        try {
            ResponseEntity<Object> response = client.getProductById(id);
            System.out.println("response.getBody() :::::::::::::::::: = " + response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.convertValue(response.getBody(), Object.class);
            System.out.println("jsonObject ::::::::::::::::::::::= " + jsonObject.toString());
            responseProductDto.setStatus(200);
            responseProductDto.setData(jsonObject);
            responseProductDto.setMessage("Product  Get  Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseProductDto;
    }

    public void delete(long id) {
        ResponseProductDto responseProductDto = new ResponseProductDto();
        try {
            ResponseEntity<Object> response = client.deleteProduct(id);
            System.out.println("response.getBody() :::::::::::::::::: = " + response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.convertValue(response.getBody(), Object.class);
            System.out.println("jsonObject ::::::::::::::::::::::= " + jsonObject.toString());
            responseProductDto.setStatus(200);
            responseProductDto.setData(jsonObject);
            responseProductDto.setMessage("Product  deleted  Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ResponseProductDto findAll() {
        ResponseProductDto responseProductDto = new ResponseProductDto();
        try {
            ResponseEntity<Object> response = client.getAllProduct();
            System.out.println("response.getBody() :::::::::::::::::: = " + response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.convertValue(response.getBody(), Object.class);
            System.out.println("jsonObject ::::::::::::::::::::::= " + jsonObject.toString());
            responseProductDto.setStatus(200);
            responseProductDto.setData(jsonObject);
            responseProductDto.setMessage("All Product  fetched  Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseProductDto;
    }
}
