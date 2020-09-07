package nl.terrax.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProcessor.class);

    @Override
    public void process(Exchange exchange) {
        LOGGER.info("Entered processor");
    }

}
