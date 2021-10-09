package com.home.pio.service.factory;

import com.home.pio.component.storage.INameGenerator;
import org.springframework.stereotype.Component;

@Component
public interface INameGeneratorFactory {
    INameGenerator create(String filename);
}
