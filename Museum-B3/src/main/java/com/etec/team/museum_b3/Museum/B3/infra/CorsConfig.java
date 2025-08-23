package com.etec.team.museum_b3.Museum.B3.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // aplica em todos os endpoints REST
                        .allowedOriginPatterns("*") // aceita requisição de qualquer origem
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // libera todos os métodos
                        .allowedHeaders("*") // aceita todos os headers
                        .allowCredentials(true); // permite cookies/credenciais se precisar
            }
        };
    }
}
