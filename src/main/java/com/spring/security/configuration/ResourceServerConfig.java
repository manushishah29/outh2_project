
package com.spring.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.cors().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.anonymous().disable()
                        .authorizeRequests().anyRequest().authenticated();

        http
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, error)->{
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    System.out.println("getAuthorities() :::" + authentication);
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                });
    }
}


