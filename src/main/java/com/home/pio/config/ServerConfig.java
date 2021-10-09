package com.home.pio.config;

import com.home.pio.component.storage.UuidNameGenerator;
import com.home.pio.service.factory.INameGeneratorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ServerConfig implements WebMvcConfigurer {
    @Bean
    public INameGeneratorFactory nameGeneratorFactory(){
        return UuidNameGenerator::new;
    }
}
