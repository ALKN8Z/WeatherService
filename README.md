# Weather Service 🌦️

Сервис для получения текущей погоды с использованием Weather API, кэшированием Caffeine и веб-интерфейсом.

## Особенности
- **Поиск погоды по городу**: температура, ощущаемый параметр, время обновления  
- **Кэширование на 10 минут** (Caffeine) для снижения нагрузки на API  
- **История последних 10 запросов** с временными метками  
- **Адаптивный веб-интерфейс** (Thymeleaf)  
- **Автоочистка кэша** по расписанию  
- **Обработка ошибок** для неверных запросов  

## Технологии
- **Backend**: Java 17, Spring Boot 3, WebClient, Spring Cache  
- **Кэширование**: Caffeine  
- **Frontend**: Thymeleaf, HTML5, CSS3  
- **Инструменты**: Lombok  

## Запуск 
1. Добавьте API-ключ OpenWeatherMap:  
   `export OPENWEATHER_API_KEY="ваш_ключ"`  
2. Соберите приложение:  
   `./mvnw clean package`  
3. Запустите сервис:  
   `java -jar target/weather-service.jar`  

**Доступно**:  
- Веб-интерфейс: `http://localhost:8080`  
- REST API: `GET /api/weather/{city}`  

## Конфигурация
```properties
# application.properties
spring.data.redis.host=localhost
weather.api.url=https://api.openweathermap.org/data/2.5
