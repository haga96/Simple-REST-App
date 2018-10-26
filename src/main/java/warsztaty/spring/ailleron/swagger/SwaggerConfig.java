package warsztaty.spring.ailleron.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8080/swagger-ui.html#/

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("nasze-api")
                .apiInfo(getApiInfo())
                .select()
                .build();
    }
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("Java Rest")
                .description("Java work")
                .version("1.0")
                .build();


    }
}

