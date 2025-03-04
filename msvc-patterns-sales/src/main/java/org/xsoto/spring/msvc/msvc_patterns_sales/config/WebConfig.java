package org.xsoto.spring.msvc.msvc_patterns_sales.config;
//package org.xsoto.spring.msvc.msvc_patterns_sales.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite CORS en todos los endpoints
                .allowedOrigins("http://localhost:3000") // Origen del frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Todos los encabezados
                .allowCredentials(true); // Si necesitas cookies o autenticación
    }
}