package nl.terrax.camel.route;

import nl.terrax.camel.logging.event.StatusEventPublisher;
import nl.terrax.camel.processor.SimpleProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;

@Component
public class SimpleFileRoute extends RouteBuilder {

    private final SimpleProcessor simpleProcessor;
    private final StatusEventPublisher statusEventPublisher;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleFileRoute.class);

    public SimpleFileRoute(SimpleProcessor simpleProcessor,
                           StatusEventPublisher statusEventPublisher) {
        this.simpleProcessor = simpleProcessor;
        this.statusEventPublisher = statusEventPublisher;
    }

    @Override
    public void configure() {
        from("file:/home/rphgoossens/test/logging_in").routeId("camel_logging")
                .log(INFO, "Picked up file")
                .process(simpleProcessor)
                .to("file:/home/rphgoossens/test/logging_out")
                .setHeader("CustomLogger", constant(LOGGER))
                .bean(statusEventPublisher, String.format("create('%s', 'Info', 'Sent file ${header.CamelFileName}')", "SimpleRoute"));
    }

}
