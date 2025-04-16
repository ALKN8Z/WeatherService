package weather_api.service.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${weather.url}")
    private String apiUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(apiUrl).build();
    }
}
