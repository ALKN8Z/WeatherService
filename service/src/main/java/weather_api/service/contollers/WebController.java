package weather_api.service.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weather_api.service.exceptions.CityNotFoundException;
import weather_api.service.services.HistoryService;
import weather_api.service.services.WeatherService;

@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WebController {
    private final WeatherService weatherService;
    private final HistoryService historyService;

    @GetMapping
    public String getWeather(@RequestParam(required = false) String city, Model model) {
        if (city != null) {
            try {
                model.addAttribute("weather", weatherService.getWeather(city));
                historyService.addCity(city);
            }catch (CityNotFoundException e){
                model.addAttribute("error", e.getMessage());
            }
        }
        model.addAttribute("history", historyService.getHistory());
        return "show";
    }
}
