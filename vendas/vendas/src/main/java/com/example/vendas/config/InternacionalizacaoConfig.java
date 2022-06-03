package com.example.vendas.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;


@Configuration
public class InternacionalizacaoConfig {// é a classe de configuração para ler o spring ler o arquivo messages.properties que contem as mensagens dos campos obrigatorios

    @Bean
    public MessageSource messageSource(){//message source é a fonte de mensagem
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");//dizemos qual arquivo vai carregar as mensagens
        messageSource.setDefaultEncoding("ISO-8859-1");//dizemos em que codificação esta nossas mensagens
        messageSource.setDefaultLocale(Locale.getDefault());//dependendo de onde a pessoa estiver acessando ela vai pegar o arquivo da rede local dela (idioma dela)
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){//responsável por fazer interpolação, pega o pedido.message algo e interpola com a mensagem do arquivo properties
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
