package com.ogul.problemservice.configuration;

import com.ogul.problemservice.client.MyClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class MyClientConfiguration {

    private static String BASE_URL = "";

    MyClient myClient() {
        WebClient webClient = WebClient.builder()
            .baseUrl(BASE_URL)
            .defaultHeader("Accept", "application/json")
            .build();

        return HttpServiceProxyFactory
            .builder(WebClientAdapter.forClient(webClient))
            .build().createClient(MyClient.class);
    }
}
