package br.com.jdo.taxone.mapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApi30Config {

  private final String moduleName = "taxone-mapper-api";
  private final String apiVersion = "/v1";

//  public OpenApi30Config(
//      @Value("${module-name}") String moduleName,
//      @Value("${api-version}") String apiVersion) {
//    this.moduleName = moduleName;
//    this.apiVersion = apiVersion;
//    System.out.println("moduleName:" + moduleName + " - apiVersion:" + apiVersion);
//  }

  @Bean
  public OpenAPI customOpenAPI() {
    final String securitySchemeName = "bearerAuth";
    final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(
            new Components()
                .addSecuritySchemes(securitySchemeName,
                    new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        )
        .info(new Info().title(apiTitle).version(apiVersion));
  }
}