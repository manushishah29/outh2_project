package com.spring.security.dto;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;


import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RequestProductDto {
    @NotEmpty(message = "productname should not be empty")
    String productname;

    @NotNull(message = "price should not be null")
    Long price;

    @NotNull(message = "qty should not be null")
    Long qty;
    @NotEmpty(message = "brand should not be null")
    String brand;

}
