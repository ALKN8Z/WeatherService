package weather_api.service.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class City {
    private String name;
    private LocalDateTime timeOfRequest;

    public City(String name, LocalDateTime timeOfRequest) {
        this.name = name;
        this.timeOfRequest = timeOfRequest;
    }
}
