package com.home.pio.config;

import com.home.pio.component.storage.LocalStorage;
import com.home.pio.component.storage.NameGenerator;
import com.home.pio.component.storage.Storage;
import com.home.pio.component.storage.UuidNameGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ServerConfig implements WebMvcConfigurer {

    @Value("${upload.basePath}")
    public Path uploadPath;

    @Bean
    public NameGenerator createNameGenerator() {
        return new UuidNameGenerator();
    }

    @Bean
    @Primary
    public Storage createStorage() {
        return new LocalStorage(Paths.get(uploadPath.toString(), "origin"));
    }

    @Bean
    @Qualifier("storage.cache")
    public Storage createCacheStorage() {
        return new LocalStorage(Paths.get(uploadPath.toString(), "cache"));
    }
}
