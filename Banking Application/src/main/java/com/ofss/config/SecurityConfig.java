package com.ofss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.ofss.service.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final CustomAuthEntryPoint authEntryPoint; // for 401 Unauthorized

    public SecurityConfig(CustomUserDetailsService userDetailsService, CustomAuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication provider bean
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Authentication manager bean
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Custom 403 Forbidden handler
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access Denied: You do not have permission to access this resource.");
        };
    }

    // Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for simplicity (not for production)
            .csrf(csrf -> csrf.disable())

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // Allow GET on /banks/** to both ADMIN and USER
                .requestMatchers(HttpMethod.GET, "/banks/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/customers/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/accounts/**").hasAnyRole("ADMIN")

                // Only ADMIN can create/update/delete
                .requestMatchers(HttpMethod.POST, "/banks/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/customers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/accounts/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT, "/banks/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/customers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/accounts/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.PATCH, "/banks/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/customers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/accounts/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/banks/**").hasRole("ADMIN")

                // Transactions — USER only
                .requestMatchers(HttpMethod.GET, "/transactions/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/transactions/**").hasRole("USER")

                // Everything else needs authentication
                .anyRequest().authenticated()
            )

            // Custom 401 unauthorized
            .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(authEntryPoint))

            // Custom 403 forbidden
            .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()))

            // Form login for browser
            .formLogin(form -> form.permitAll())

            // Logout allowed
            .logout(logout -> logout.permitAll())

            // Stateless sessions — always asks for login
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
