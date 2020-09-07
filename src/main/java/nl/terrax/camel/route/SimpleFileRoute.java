package nl.terrax.camel.route;

import nl.terrax.camel.processor.SimpleProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;

@Component
public class SimpleFileRoute extends RouteBuilder {

    private final SimpleProcessor simpleProcessor;

    public SimpleFileRoute(SimpleProcessor simpleProcessor) {
        this.simpleProcessor = simpleProcessor;
    }

    @Override
    public void configure() {
        from("file:/home/rphgoossens/test/logging_in").routeId("camel_logging")
                .log(INFO, "Picked up file")
                .process(simpleProcessor)
                .to("file:/home/rphgoossens/test/logging_out");
    }

}
