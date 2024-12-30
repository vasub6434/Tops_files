package com.example.token1.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;



@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")

public class SwaggerConfig {

	@Bean
	public  OpenAPI springOpenAPI() {
		
		 final String securitySchemeName = "bearerAuth";

		 
//		 return new OpenAPI()
//				 .info(new Info().title("springboot API").description("spring boot").version("v0.0.1").license(new License().name("vasu").url("API license url")))
//				 .externalDocs(new ExternalDocumentation().description("spring boot1").url("vasu"));
						 
						
		 return new OpenAPI()
				 .info(new Info().title("SpringBoot API")
			     .description("spring boot sample application")
			     .version("v0.0.1")
			     .license(new License().name("archit").url("API license URL")))
				 .externalDocs(new ExternalDocumentation()
				 .description("spring boot")		 
				 .url("https://springboot.wiki.github.org/docs"));
	}

}