package senizm.integration.dsl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import senizm.integration.dsl.integration.handler.EventHandler;
import senizm.integration.dsl.service.EventService;

@Configuration
public class IntegrationHandlerConfiguration {

    @Bean
    public EventHandler eventHandler(EventService eventService) {
        return new EventHandler(eventService);
    }
}
