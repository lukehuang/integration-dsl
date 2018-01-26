package senizm.integration.dsl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import senizm.integration.dsl.persistence.repository.EventRepository;
import senizm.integration.dsl.service.EventPostService;
import senizm.integration.dsl.service.EventService;

@Configuration
public class ServiceConfiguration {

    @Bean
    public EventService eventService(EventRepository eventRepository) {
        return new EventService(eventRepository);
    }

    @Bean
    public EventPostService eventPostService(EventService eventService) {
        return new EventPostService(eventService);
    }
}