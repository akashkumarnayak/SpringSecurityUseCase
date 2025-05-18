package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.configs;

import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.security.DBUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DBUserDetailService dbUserDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/v1/user/create","/v1/user/signin").permitAll()
                                .requestMatchers("/v1/user/update").hasRole("ADMIN")//permits the signup api in path /v1/user/create without any authentication
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sessionManagement ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(dbUserDetailService);
        return (AuthenticationProvider) provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }
}
