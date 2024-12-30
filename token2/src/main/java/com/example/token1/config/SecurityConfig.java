package com.example.token1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.token1.security.JwtAuthenticationEntryPoint;
import com.example.token1.security.JwtAuthenticationFilter;

@Configuration
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SecurityConfig {

	

     
    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public final static String[] PUBLIC_REQUEST_MATCHERS = { 
		    "/swagger-ui/**", 
		    "/swagger-ui.html", 
		    "/v3/api-docs/**", 
		    "/swagger-resources/**" 
		};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(PUBLIC_REQUEST_MATCHERS).permitAll()  // Public paths (Swagger and Auth)
                .requestMatchers("/home/**").authenticated()  // Secured paths
                .requestMatchers("/auth/login").permitAll()  // Login should be accessible without authentication
                .requestMatchers("/auth/createuser/**").permitAll()  // User creation should also be open
                .anyRequest().authenticated())  // Any other request requires authentication

            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider doDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
