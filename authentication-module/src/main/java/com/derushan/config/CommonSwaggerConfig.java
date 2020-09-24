/**
 * 
 */
package com.derushan.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Derushan Sep 21, 2020
 */
@Configuration 
@EnableSwagger2
public class CommonSwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(generateAPIInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.derushan.controllers")).paths(PathSelectors.any())
				.build()
				.produces(getAllProduceContentTypes())
				.securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()));
	}

	private ApiInfo generateAPIInfo() {
		return new ApiInfo("Common Api", "Implementing Swagger with Common Multimodule SpringBoot Application", "0.0.1",
				"https://example.com/", getContacts(), "", "");
	}

	// Developer Contacts
	private Contact getContacts() {
		return new Contact("Kanagaratnam Derushan", "", "k.derushan@gmail.com");
	}
	
	 private ApiKey apiKey() {
	        return new ApiKey("Bearer", "Authorization", "header");
	    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Bearer",
                authorizationScopes));
	}
	
	private Set<String> getAllProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        // Add other media types if required in future
        produces.add("application/json");
        return produces;
    }
}
