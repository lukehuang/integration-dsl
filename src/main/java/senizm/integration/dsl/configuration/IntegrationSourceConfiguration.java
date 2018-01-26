package senizm.integration.dsl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jpa.core.JpaExecutor;
import org.springframework.integration.jpa.inbound.JpaPollingChannelAdapter;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Configuration
public class IntegrationSourceConfiguration {

    @Bean
    @Transactional
    public JpaExecutor incomingEventsJpaExecutor(EntityManager entityManager) {
        final JpaExecutor jpaExecutor = new JpaExecutor(entityManager);
        jpaExecutor.setJpaQuery("select e from Event e where e.status=2 order by e.insertDate");
        jpaExecutor.setDeleteAfterPoll(true);
        jpaExecutor.setDeleteInBatch(true);
        jpaExecutor.setFlush(true);
        return jpaExecutor;
    }

    @Bean
    public JpaPollingChannelAdapter incomingEventsJpaChannelAdapter(JpaExecutor incomingEventsJpaExecutor) {
        return new JpaPollingChannelAdapter(incomingEventsJpaExecutor);
    }
}