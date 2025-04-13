package weather_api.service.contollers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import weather_api.service.dto.WeatherResponse;
import weather_api.service.exceptions.CityNotFoundException;
import weather_api.service.services.HistoryService;
import weather_api.service.services.WeatherService;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    private final HistoryService historyService;

    @PostMapping("/{city}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String city) throws CityNotFoundException {
        historyService.addCity(city);
        return ResponseEntity.ok(weatherService.getWeather(city));
    }
}
