//package com.example.interceptor;
//
//import com.example.entity.RequestResponse;
//import com.example.repository.LogRepository;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//@Component
//@RequiredArgsConstructor
//public class LoggingFilter extends OncePerRequestFilter {
//
//    private final LogRepository logRepository;
//    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
//        System.out.println("request.getHeader(\"x-api-key\") = " + request.getHeader("x-api-key"));
//
//        long startTime = System.currentTimeMillis();
//        filterChain.doFilter(requestWrapper, responseWrapper);
//        long timeTaken = System.currentTimeMillis() - startTime;
//
//        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
//                request.getCharacterEncoding());
//        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
//                response.getCharacterEncoding());
//
//        LOGGER.info(
//                "FINISHED PROCESSING : METHOD={}; REQUESTURI={}; REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}; TIM TAKEN={}",
//                request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
//                timeTaken);
//        LOGGER.info("request body is :::::::::{}", requestBody);
//        LOGGER.info("response body is :::::::::{}", responseBody);
//
//        RequestResponse requestResponse = new RequestResponse();
//        requestResponse.setResponse(responseBody);
//        requestResponse.setRequest(requestBody);
//        logRepository.save(requestResponse);
////        LOGGER.info("logRepository call is {}" + save);
////        responseWrapper.copyBodyToResponse();
//
//    }
//
//
//    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
//        try {
//            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//}
