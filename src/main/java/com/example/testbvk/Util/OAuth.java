package com.example.testbvk.Util;


import com.example.testbvk.Service.UserInterface;
import com.example.testbvk.Util.Security.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth{

    private final JwtRequestFilter jwtRequestFilter;
    private final UserInterface userInterface;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userInterface.userDetailsService());
        return authProvider;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers("/testBVK/user/registerUser").permitAll()
                            .requestMatchers("/testBVK/user/login").permitAll()
                            .requestMatchers("/testBVK/user/oauth2/redirect").permitAll()
                            .requestMatchers("/testBVK/member/create").permitAll()
                            .requestMatchers("/testBVK/member/listMember").permitAll()
                            .requestMatchers("/testBVK/member/detailMember").permitAll()
                            .requestMatchers("/uploads/**").permitAll()
                            .anyRequest().authenticated();
                })
                .oauth2Login()
                .defaultSuccessUrl("http://localhost:3000/after-login", true)
                .and()
                .cors()
                .and()
                .csrf().disable(); // Consider enabling this for security
        return http.build();
    }

}
