package com.sap.cds.lsoadmin.config;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import com.opencsv.bean.CsvToBeanBuilder;
import com.sap.cds.lsoadmin.exceptions.EX_LSOADMIN;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_MessageTypeDesc;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_MessagesTypeDesc;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@PropertySources(
{ @PropertySource("classpath:messages.properties"), })
public class PropertyConfig
{
    @Autowired
    private MessageSource msgSrc;

    private final String configMsgTypesTemplates = "/configs/msgTypes.csv";

    @Bean
    public ResourceBundleMessageSource messageSource()
    {

        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.addBasenames("messages");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }

    @Bean("MessageTypesConfig")
    public TY_MessagesTypeDesc loadMsgTypesConfig()
    {
        TY_MessagesTypeDesc msgTypesConfig = null;

        try
        {

            ClassPathResource classPathResource = new ClassPathResource(configMsgTypesTemplates);
            if (classPathResource != null)
            {
                Reader reader = new InputStreamReader(classPathResource.getInputStream());
                if (reader != null)
                {
                    log.info("Resource Bound... ");
                    List<TY_MessageTypeDesc> configs = new CsvToBeanBuilder(reader).withSkipLines(1)
                            .withType(TY_MessageTypeDesc.class).build().parse();

                    if (!CollectionUtils.isEmpty(configs))
                    {
                        log.info("Entries in Config. Found for Message Type(s) " + configs.size());
                        msgTypesConfig = new TY_MessagesTypeDesc(configs);
                    }
                }
            }

        }
        catch (Exception e)
        {
            throw new EX_LSOADMIN(msgSrc.getMessage("ERR_MSGTYPE_CFG", new Object[]
            { configMsgTypesTemplates, e.getLocalizedMessage() }, Locale.ENGLISH));
        }

        return msgTypesConfig;
    }

}
