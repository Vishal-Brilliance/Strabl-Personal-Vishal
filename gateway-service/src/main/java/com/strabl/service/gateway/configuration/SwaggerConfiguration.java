package com.strabl.service.gateway.configuration;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.bean.validators.plugins.Validators;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Strabl Backend")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.strabl"))
        .paths(PathSelectors.any())
        .build()
        .securityContexts(securityContexts())
        .securitySchemes(securitySchemes())
        .apiInfo(
            new ApiInfo(
                "Strabl Backend - Gateway",
                "Gateway service for Strabl Backend Microservices",
                "Version 1.0",
                null,
                null,
                null,
                null,
                Collections.emptyList()
            )
        );
  }

  private List<SecurityContext> securityContexts() {

    return List.of(
        SecurityContext
            .builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.ant("/v1/api/**"))
            .build()
    );
  }

  private List<SecurityReference> defaultAuth() {

    AuthorizationScope authorizationScope = new AuthorizationScope(
        "Registered User",
        "Access to resources only available to registered users."
    );

    return List.of(
        new SecurityReference("JWT", new AuthorizationScope[]{ authorizationScope })
    );
  }

  private List<? extends SecurityScheme> securitySchemes() {
    return List.of(
        new ApiKey("JWT", "Authorization", "header")
    );
  }
}

/*
We need this bean because Springfox Swagger Validator does not by default support @NotBlank validator
REF: https://github.com/swagger-api/swagger-core/issues/2778
 */
@Component
@Order(Validators.BEAN_VALIDATOR_PLUGIN_ORDER)
class ExpandedParameterNotBlankAnnotationPlugin implements ModelPropertyBuilderPlugin {

  @Override
  public boolean supports(DocumentationType delimiter) {
    // Support all documentationTypes
    return true;
  }

  @Override
  public void apply(ModelPropertyContext context) {
    Optional<NotBlank> notBlank = extractAnnotation(context);
    if (notBlank.isPresent()) {
      context.getBuilder().required(true);
    }
  }

  @VisibleForTesting
  Optional<NotBlank> extractAnnotation(ModelPropertyContext context) {
    return Validators.annotationFromBean(context, NotBlank.class).or(Validators.annotationFromField(context, NotBlank.class));
  }
}

