package com.example.usuarioapi.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.example.usuarioapi")
@PropertySource("classpath:application.properties")
public class ConfiguradorSpring {
}
