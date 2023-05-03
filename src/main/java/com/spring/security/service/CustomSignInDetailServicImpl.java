//package com.spring.security.service;
//
//import com.spring.security.model.SignIn;
//import com.spring.security.repository.SignInDetailsRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class CustomSignInDetailServicImpl implements CustomSignInDetailService {
//    @Autowired
//    SignInDetailsRepo loginDetailsRepo;
//
//
//    @Override
//    public SignIn save(SignIn signIn) {
//
//        signIn.setUsername("admin");
//        signIn.setPassword("admin");
//        return this.loginDetailsRepo.save(signIn);
//    }
//}
