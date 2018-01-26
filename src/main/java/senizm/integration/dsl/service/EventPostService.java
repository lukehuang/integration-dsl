package senizm.integration.dsl.service;

import lombok.RequiredArgsConstructor;
import senizm.integration.dsl.persistence.entity.Event;

import javax.annotation.PostConstruct;
import java.util.Date;

@RequiredArgsConstructor
public class EventPostService {

	private final EventService eventService;

	@PostConstruct
	public void addEvent()  {
		eventService.save(Event.builder().eventId(1).status(2).insertDate(new Date()).build());
	}
}
