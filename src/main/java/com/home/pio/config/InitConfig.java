package com.home.pio.config;

import com.home.pio.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.naming.ConfigurationException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class InitConfig {

    @Value("${upload.basePath}")
    public Path basePath;

    @Autowired
    private NodeService nodeService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws ConfigurationException {
        this.checkFileDirectory();
        nodeService.createDefaultNode();
    }

    private void checkFileDirectory() throws ConfigurationException {

        if(basePath == null){
            throw new ConfigurationException("Files directory not set");
        }

        if(!Files.exists(basePath)){
            throw new ConfigurationException("Files directory not exist");
        }

        if(!Files.isWritable(basePath)){
            throw new ConfigurationException("Files directory not writable");
        }
    }

}
