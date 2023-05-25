package com.demo.client;


import com.demo.dto.RequestProductDto;
import com.demo.dto.ResponseProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomProductDetailService {
    ResponseProductDto save(RequestProductDto requestProductDto);
       List<ResponseProductDto> findAll();
      ResponseProductDto findById(Long id);
       void delete(Long id);
       ResponseProductDto update(RequestProductDto requestProductDto,Long id);

//       List<ResponseProductDto> findByProductName(String productname);
}
