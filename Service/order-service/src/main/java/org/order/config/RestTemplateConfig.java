package org.order.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){return builder.build();}



    public <T> T getForObject(String url, Class<T> responseType){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, responseType);

    }

}
