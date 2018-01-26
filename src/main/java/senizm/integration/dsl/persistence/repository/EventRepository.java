package senizm.integration.dsl.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senizm.integration.dsl.persistence.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
