package com.spring.security.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class ResponseProductDto {

    Long id;
    String productname;
    Long price;
    Long qty;
    String brand;
}