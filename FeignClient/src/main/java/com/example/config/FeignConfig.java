package com.example.config;

import com.example.interceptor.MyInterceptor;
import com.example.repository.LogRepository;
import feign.RequestInterceptor;
import feign.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class FeignConfig {

    private final LogRepository logRepository;
    @Value("${security.authorization.key}")
    private String authorizationKey;

    @Bean
    public RequestInterceptor imtUserFeignRequestInterceptor() {
        // mapping current request header to feign imt user request
        return template -> template.header("x-api-key", this.authorizationKey);
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient(
                new okhttp3.OkHttpClient.Builder()
                        .addInterceptor(new MyInterceptor(logRepository))
                        .build());
    }

}
