package com.mohsinkd786.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class HelloSettings {

    @Value("${external.service.hello.url}")
    private String helloBaseUrl;

}
