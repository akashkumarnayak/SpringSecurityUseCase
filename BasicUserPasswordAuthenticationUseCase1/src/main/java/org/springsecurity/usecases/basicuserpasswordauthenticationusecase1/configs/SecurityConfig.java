package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.configs;

import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.security.DBUserDetailService;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.security.DBUserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    DBUserDetailService dbUserDetailService;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/v1/user/create").permitAll()
                                .requestMatchers("/v1/user/update").hasRole("ADMIN")//permits the signup api in path /v1/user/create without any authentication
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(dbUserDetailService);

        return (AuthenticationProvider) provider;
    }
}
