package com.spring.security.repository;


import com.spring.security.dto.ResponseProductDto;
import com.spring.security.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepo extends JpaRepository<Product,Long> {


}
