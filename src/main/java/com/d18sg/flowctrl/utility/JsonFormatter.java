/*
 *    Copyright 2023 Kelvin Johnson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
