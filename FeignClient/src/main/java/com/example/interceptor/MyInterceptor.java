package com.example.interceptor;

import com.example.entity.RequestResponse;
import com.example.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MyInterceptor implements Interceptor {
    private final LogRepository logRepository;
    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();

        long t1 = System.nanoTime();
        logger.info(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        logger.info("request  is :::::{}", request);


        Response response = chain.proceed(request);
        logger.info("response is :::::{}", response);
        long t2 = System.nanoTime();


        RequestResponse requestResponse = new RequestResponse();

        RequestBody requestBody = request.body();

        Buffer buffer = new Buffer();
        if (requestBody != null) {
            requestBody.writeTo(buffer);
            String rBody = buffer.readUtf8();
            requestResponse.setRequestBody(rBody);
        } else {
            requestResponse.setRequestBody(" ");
        }

        if (response != null) {
            String responseData = response.newBuilder().body(response.peekBody(Long.MAX_VALUE)).build().body().string();
            requestResponse.setResponse(responseData);
        } else {
            requestResponse.setResponse("");
        }
        requestResponse.setRequestUrl(request.url().toString());
        requestResponse.setRequestMethod(request.method());
        logRepository.save(requestResponse);
        return response;
    }
}
