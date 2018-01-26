package senizm.integration.dsl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import senizm.integration.dsl.persistence.entity.Event;
import senizm.integration.dsl.persistence.repository.EventRepository;

@Slf4j
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;

	public Event save(Event event)  {
		Event savedEvent = this.eventRepository.save(event);
		log.info("Event saved: " + savedEvent.getEventId());
		return savedEvent;
	}
}
