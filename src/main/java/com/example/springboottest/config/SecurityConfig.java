package com.example.springboottest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// //@RequiredArgsConstructor
// public class SecurityConfig {
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf(AbstractHttpConfigurer::disable)
//                 .authorizeHttpRequests((authorize) -> authorize
//                         //.requestMatchers("/healthcheck", "/register", "/login").permitAll() // APIs not required auth
//                         //.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // swagger-ui
//                         .anyRequest().permitAll());
//         return http.build();
//     }
// }

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            authorize -> authorize
                .requestMatchers("/healthcheck", "/hello").permitAll()
                .anyRequest().authenticated())
            .oauth2Login(Customizer.withDefaults())
            .logout(
                logout -> logout
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies()
                    .clearAuthentication(true)
                    .logoutSuccessUrl("https://login.microsoftonline.com/65f2ec61-0212-4426-b9cf-f8218312e160/oauth2/v2.0/logout?post_logout_redirect_uri=http://localhost:8080/")
            );
        return http.build();
    }
}
