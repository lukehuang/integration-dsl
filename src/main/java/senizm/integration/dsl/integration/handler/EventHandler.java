package senizm.integration.dsl.integration.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import senizm.integration.dsl.persistence.entity.Event;
import senizm.integration.dsl.service.EventService;

@Slf4j
@RequiredArgsConstructor
public class EventHandler implements MessageHandler {

    private final EventService eventService;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            final Event event = (Event) message.getPayload();
            log.info("Event to be processed. EventId : " + event.getEventId());
        } catch (ClassCastException cce) {
            log.error("Unexpected payload type:{}", message.getPayload().getClass().getSimpleName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
