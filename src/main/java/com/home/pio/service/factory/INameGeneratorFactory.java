package com.home.pio.service.factory;

import com.home.pio.component.storage.NameGenerator;
import org.springframework.stereotype.Component;

@Component
public interface INameGeneratorFactory {
    NameGenerator create(String filename);
}
