package com.sap.cds.lsoadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@PropertySources(
{ @PropertySource("classpath:messages.properties"), })
public class PropertyConfig
{
    @Bean
    public ResourceBundleMessageSource messageSource()
    {

        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.addBasenames("messages");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
}
