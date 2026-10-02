package com.example.biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPiConfig {

    @Bean// Lo ejecuta de forma inmediata con el proyecto
    public OpenAPI blibliotecaOpenApi(){
        return new OpenAPI().info(new Info().title("API Biblioteca").version("1.0.0").description("API para registrar autores, consultar sus libros " + "y agregar libros a un autor"));
    }
}
