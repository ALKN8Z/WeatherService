package weather_api.service.services;

import org.springframework.stereotype.Service;
import weather_api.service.models.City;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class HistoryService {
    private final Queue<City> history = new LinkedList<>();
    private final static int MAX_HISTORY_SIZE = 10;

    public void addCity(String city) {
        synchronized (history) {
            history.add(new City(city, LocalDateTime.now()));
            if (history.size() > MAX_HISTORY_SIZE) {
                history.poll();
            }
        }
    }

    public Queue<City> getHistory() {
        return history;
    }
}
