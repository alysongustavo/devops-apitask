package br.com.alysonrodrigo.praticaapitask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable) // Desabilita o CSRF
            .authorizeHttpRequests(authorize ->
                    authorize
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permitir acesso público a "/v3/api-docs/**"
                            .anyRequest().authenticated() // Exigir autenticação para qualquer outra solicitação
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults());


        return http.build();
    }
}
