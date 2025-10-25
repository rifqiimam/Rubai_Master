package com.rubai.rubai_master.configurations

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
// Remove this line: @OpenAPIDefinition
class SpringDocConfig {
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Documentation RUBAI Master APP")
                    .description("RUBAI Service Documentation")
                    .version("1.0")
                    .contact(
                        Contact()
                            .email("imamramadhan.rifqi@gmail.com")
                            .name("rifqi")
                    )
                    .license(
                        License()
                            .name("PT. RUBAI Copyright © 2023")
                    )
            )
    }
}