package com.demo.client;


import com.demo.dto.RequestProductDto;
import com.demo.dto.ResponseProductDto;
import com.demo.entity.Product;
import com.demo.enums.CustomEnums;
import com.demo.exception.CustomException;
import com.demo.repository.ProductDetailRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomProductDetailServiceImpl implements CustomProductDetailService {

    private final ProductDetailRepo productDetailRepo;

    private final ModelMapper modelMapper;


    @Override
    public ResponseProductDto save(RequestProductDto requestProductDto) {

        Product product = this.productDetailRepo.save(modelMapper.map(requestProductDto, Product.class));
        return modelMapper.map(product, ResponseProductDto.class);
    }

    @Override
    public ResponseProductDto update(RequestProductDto dto, Long id) {
        Product product = this.searchById(id);
        product.setBrand(dto.getBrand());
        product.setQty(dto.getQty());
        product.setProductname(dto.getProductname());
        product.setPrice(dto.getPrice());
        product = productDetailRepo.save(product);
        return modelMapper.map(product, ResponseProductDto.class);
    }



    @Override
        public List<ResponseProductDto> findAll() {
            List<Product> ls= this.productDetailRepo.findAll();
            return ls.stream().map(this::mapToResponseProductDto
                    ).collect(Collectors.toList());
        }

    @Override
    public ResponseProductDto findById(Long id) {
        return modelMapper.map(this.searchById(id), ResponseProductDto.class);
    }

    private Product searchById(Long id){
        return productDetailRepo.findById(id).orElseThrow(()->
                new CustomException(CustomEnums.NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.searchById(id);
        this.productDetailRepo.deleteById(id);
    }

    public ResponseProductDto mapToResponseProductDto(Product product){
        return modelMapper.map(product, ResponseProductDto.class);
    }

}
