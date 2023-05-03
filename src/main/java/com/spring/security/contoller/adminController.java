package com.spring.security.contoller;

import com.spring.security.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RestController
public class adminController {

    @GetMapping("/admin")
    public String mess(){
        return "hello admin";
    }
//
//    @GetMapping("/login")
//    public ResponseEntity<ApiResponse> login{
//        return
//    }

}
