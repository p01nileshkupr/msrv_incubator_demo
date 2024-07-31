package com.nileshprajapati.incubator_demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfiguration {

    private final EnvironmentConfigProperties environmentConfigProperties;

    @Autowired
    public ApplicationConfiguration(EnvironmentConfigProperties environmentConfigProperties) {
        this.environmentConfigProperties = environmentConfigProperties;
    }

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl(environmentConfigProperties.getServerHost());
        server.setDescription(environmentConfigProperties.getServerType());

        Contact myContact = new Contact();
        myContact.setName(environmentConfigProperties.getDevName());
        myContact.setEmail(environmentConfigProperties.getDevEmail());

        Info information = new Info()
                .title(environmentConfigProperties.getApiTitle())
                .version(environmentConfigProperties.getApiVersion())
                .description(environmentConfigProperties.getApiDescription())
                .contact(myContact);

        return new OpenAPI()
                .info(information)
                .servers(List.of(server));
    }
}