package com.d18sg.flowctrl.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonFormatter {
    private static final Logger logger = LoggerFactory.getLogger(JsonFormatter.class);

    private ObjectMapper mapper = new ObjectMapper();

    public String format(String response, String printOption) {
        try {
            Object json = mapper.readValue(response, Object.class);
            String formattedResponse;
            Printing printing = Printing.valueOf(printOption.toUpperCase());
            switch (printing) {
                case RAW:
                    logger.debug("RAW");
                    formattedResponse = response;
                    break;
                case COMPACT:
                    logger.debug("COMPACT");
                    formattedResponse = mapper.writeValueAsString(json);
                    break;
                case PRETTY:
                    logger.debug("PRETTY");
                    formattedResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                    break;
                default:
                    logger.debug("DEFAULT");
                    formattedResponse = mapper.writeValueAsString(json);
            }
            return formattedResponse;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
