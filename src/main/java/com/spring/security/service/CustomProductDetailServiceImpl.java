package com.spring.security.service;

import com.spring.security.dto.RequestProductDto;
import com.spring.security.dto.ResponseProductDto;
import com.spring.security.enums.CustomEnums;
import com.spring.security.exception.CustomException;
import com.spring.security.model.Product;
import com.spring.security.repository.ProductDetailRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomProductDetailServiceImpl implements CustomProductDetailService {
    @Autowired
    ProductDetailRepo productDetailRepo;
    private ModelMapper modelMapper = new ModelMapper();



    @Override
    public ResponseProductDto add(RequestProductDto requestProductDto) {

        Product product = this.productDetailRepo.save(modelMapper.map(requestProductDto, Product.class));
        return modelMapper.map(product, ResponseProductDto.class);
    }

    @Override
    public Page getAll(Pageable pageable) {
//        Page<Product> ls= this.productDetailRepo.findAll(pageable);
//        return ls.stream().map(this::mapToResponseProductDto);
        return productDetailRepo.findAll(pageable);

    }


    @Override
    public ResponseProductDto update(RequestProductDto dto, Long id) {
        Product product = this.find(id);
        product.setBrand(dto.getBrand());
        product.setQty(dto.getQty());
        product.setProductname(dto.getProductname());
        product.setPrice(dto.getPrice());
        product = productDetailRepo.save(product);
        return modelMapper.map(product, ResponseProductDto.class);
    }

    @Override
    public ResponseProductDto searchById(Long id) {
        return modelMapper.map(this.find(id), ResponseProductDto.class);
    }

    private Product find(Long id){
        return productDetailRepo.findById(id).orElseThrow(()->
                new CustomException(CustomEnums.NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        this.productDetailRepo.deleteById(id);
    }

    public ResponseProductDto mapToResponseProductDto(Product product){
        return modelMapper.map(product, ResponseProductDto.class);
    }
}
