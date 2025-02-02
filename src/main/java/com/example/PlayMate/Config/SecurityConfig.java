package com.example.PlayMate.Config;

import com.example.PlayMate.Entity.Post;
import com.example.PlayMate.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/CSS/**", "/JS/**,/image/**").permitAll()
                        .requestMatchers("/api/users/register", "/login.html", "/registration.html","Profile.html","EditProfile.html").permitAll()  // Allow registration and login pages without authentication
                        .requestMatchers("/login").permitAll() // Exclude error page from security
                        .requestMatchers(HttpMethod.GET, "/api/games/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/posts/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "api/profile/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/games/{gameId}/posts/{postId}/comments/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/posts/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/games/{gameId}/posts/{postId}/comments/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "api/profile/**").hasAnyRole("USER", "ADMIN")




                        .requestMatchers("/admin.html").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form

                        .loginPage("/login.html") // Custom login page URL
                        .loginProcessingUrl("/perform_login") // URL where the login form should POST data
                        .defaultSuccessUrl("/index.html", true) // Redirect after successful login
                        .failureUrl("/login.html") // Redirect in case of failed login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .userDetailsService(customUserDetailsService)
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity (Enable if needed)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
