package org.customer.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class RestTemplateConfig {

    @Value("${rate.limiting.permitsPerSecond}")
    private double permitsPerSecond;

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(permitsPerSecond);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, RateLimiter rateLimiter) {
        return builder
                .additionalInterceptors(new RateLimitingInterceptor(rateLimiter))
                .build();
    }

    private static class RateLimitingInterceptor implements ClientHttpRequestInterceptor {
        private final RateLimiter rateLimiter;

        public RateLimitingInterceptor(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
        }

        @Override
        public org.springframework.http.client.ClientHttpResponse intercept(
                org.springframework.http.HttpRequest request, byte[] body,
                org.springframework.http.client.ClientHttpRequestExecution execution) throws IOException {
            if (rateLimiter.tryAcquire()) {
                System.out.println("Rate limit acquired");
                return execution.execute(request, body);
            } else {
                System.out.println("Rate limit exceeded");
                throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS, "Rate limit exceeded. Try again later.");
            }
        }
    }
}
