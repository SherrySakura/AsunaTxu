package com.asuna.textutils.config;

import com.asuna.textutils.entity.TranslateAPIProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GenerateConfig {

    @Bean
    public TranslateAPIProperty getTranslateAPIProperty(){
        return new TranslateAPIProperty();
    }
}
