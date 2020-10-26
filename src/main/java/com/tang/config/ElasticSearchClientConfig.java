package com.tang.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ElasticSearchClientConfig {
    @Value("${elastic-search-client-config.hostname}")
    private String hostname;
    @Value("${elastic-search-client-config.port}")
    private int port;
    @Value("${elastic-search-client-config.scheme}")
    private String scheme;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostname, port, scheme)
                ));
    }

}
