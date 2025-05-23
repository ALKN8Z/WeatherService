package weather_api.service.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;
import reactor.core.publisher.Mono;
import weather_api.service.dto.WeatherResponse;
import weather_api.service.exceptions.CityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final WebClient webClient;

    @Cacheable(value = "weather-cache", key = "#city")
    public WeatherResponse getWeather(String city) {

        log.info("Получаем информацию о погоде в городе - {}", city);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("q", city)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error
                                (new CityNotFoundException("Введенный город - " + city + "  не найден")))
                .bodyToMono(WeatherResponse.class)
                .block();
    }
}
