//package com.spring.security.contoller;
//
//import com.spring.security.model.SignIn;
//import com.spring.security.response.ApiResponse;
//import com.spring.security.service.CustomSignInDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//
//public class SignInController {
//
//    @Autowired
//    CustomSignInDetailService customSignInDetailService;
//    @GetMapping("/signIn")
//    public ResponseEntity<ApiResponse> signIn(@RequestBody  SignIn signIn)
//    {
////        ResponseProductDto responseProductDto = this.customProductDetailService.save(requestProductDto);
//        SignIn login=this.customSignInDetailService.save(signIn);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Product Added Successfully", login),
//                HttpStatus.OK);
//    }
//}
