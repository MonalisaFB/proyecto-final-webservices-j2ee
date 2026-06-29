package com.unaempresa.examen_final.config;

import com.unaempresa.examen_final.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**", "/webjars/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()

                .requestMatchers("/preguntas/nueva", "/preguntas/guardar").hasRole("ADMIN")
                .requestMatchers("/preguntas/editar/**", "/preguntas/eliminar/**").hasRole("ADMIN")
                .requestMatchers("/tematicas/nueva", "/tematicas/guardar").hasRole("ADMIN")
                .requestMatchers("/tematicas/editar/**", "/tematicas/eliminar/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/preguntas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/preguntas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/preguntas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/preguntas/**").authenticated()

                .anyRequest().permitAll()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )

            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}