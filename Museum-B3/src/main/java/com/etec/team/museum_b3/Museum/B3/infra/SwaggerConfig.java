package com.etec.team.museum_b3.Museum.B3.infra;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Museu da Bolsa de Valores",
                version = "1.0.0",
                description = "Essa é uma 4API de quiz e informações sobre o museu da bolsa de valores, que foi solicitada como trabalho escolar na etec de cidade tiradentes",
                license = @License(
                        name = "Creative Commons",
                        url = "https://creativecommons.org/share-your-work/cclicenses/"
                )

        )
)
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Finance API")
                        .description("API para gerenciamento financeiro pessoal com autenticação JWT")
                        .version("1.0.0"));
    }

}
