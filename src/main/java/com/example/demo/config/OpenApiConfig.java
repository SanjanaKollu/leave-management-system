package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

                .info(new Info()
                        .title("Leave Management System API")
                        .version("1.0")
                        .description("REST APIs for Leave Management System using Spring Boot and JWT Authentication.")
                        .contact(new Contact()
                                .name("Sanjana Reddy")
                                .email("sanjana@gmail.com")))

                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                .schemaRequirement(
                        securitySchemeName,
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));
    }
}