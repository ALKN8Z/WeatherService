package weather_api.service.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CacheCleanUpScheduler {
    private final CacheManager cacheManager;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void cleanUp() {
        try {
            log.info("Очищаем кэш");
            Cache cache = cacheManager.getCache("weather-cache");
            if (cache != null) {
                log.info("Кэш успешно очищен");
                cache.clear();
            } else {
                log.info("Кэш не найден");
            }
        } catch (Exception e) {
            log.error("Ошибка при очистке кэша: {}", e.getMessage());
        }

    }
}
