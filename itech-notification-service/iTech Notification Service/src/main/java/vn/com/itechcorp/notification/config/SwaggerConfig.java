package vn.com.itechcorp.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket SecuredApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Secured API")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/secured/ws/rest/v1/**"))
                .build()
                .ignoredParameterTypes(Principal.class);
    }
}
