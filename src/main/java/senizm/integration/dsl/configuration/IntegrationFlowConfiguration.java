package senizm.integration.dsl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.jpa.inbound.JpaPollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import senizm.integration.dsl.integration.gateway.IncomingEventsGateway;
import senizm.integration.dsl.integration.handler.EventHandler;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.Executors;

@Configuration
@EnableIntegration
@EnableConfigurationProperties
public class IntegrationFlowConfiguration {

    @Value("${integration.poller.interval}")
    private Integer interval;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(this.entityManagerFactory);
    }

    @Bean(name = IncomingEventsGateway.INCOMING_EVENTS_CHANNEL)
    public MessageChannel incomingEventsChannel() {
        return new QueueChannel(Integer.MAX_VALUE);
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPollerMetadata() {
        return Pollers.fixedDelay(interval).transactional(transactionManager()).get();
    }

    @Bean
    public IntegrationFlow eventProcessingFlow(JpaPollingChannelAdapter incomingEventsJpaChannelAdapter, EventHandler eventHandler) {
        return IntegrationFlows
            .from(incomingEventsJpaChannelAdapter)
            .split()
            .channel(MessageChannels.executor(Executors.newFixedThreadPool(5)))
            .handle(eventHandler)
            .get();
    }
}
