package com.spring.security.service;


import com.spring.security.dto.RequestProductDto;
import com.spring.security.dto.ResponseProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomProductDetailService {
    ResponseProductDto add(RequestProductDto requestProductDto);

    Page<ResponseProductDto> getAll(Pageable pageable);

    ResponseProductDto searchById(Long id);

    void delete(Long id);

    ResponseProductDto update(RequestProductDto requestProductDto, Long id);
}
