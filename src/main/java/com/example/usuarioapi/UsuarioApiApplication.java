package com.example.usuarioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
@EntityScan({"com.example.usuarioapi"})
public class UsuarioApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioApiApplication.class, args);
    }


    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.usuarioapi"))
                .paths(PathSelectors.any()).build().apiInfo(getApiInfo());

    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Usuarios API", "Metodos necesarios para la creacion de usuarios ", "1.0.0", "Rest",
                new Contact("www.usuarios.com", "http://google.com/", "smartBit@email.com"), "2024 - Licencia",
                "http://google.com/", Collections.emptyList());
    }
}
