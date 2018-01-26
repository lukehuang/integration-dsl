package senizm.integration.dsl.integration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import senizm.integration.dsl.persistence.entity.Event;

@MessagingGateway
public interface IncomingEventsGateway {

    String INCOMING_EVENTS_CHANNEL = "incomingEventsChannel";

    @Gateway(requestChannel = INCOMING_EVENTS_CHANNEL)
    void processIncomingEvents(final Event event);
}
