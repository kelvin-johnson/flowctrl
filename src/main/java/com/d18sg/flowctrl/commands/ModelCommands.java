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

package com.d18sg.flowctrl.commands;

import com.codernaught.wafle.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.jline.terminal.Terminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.CommandRegistration;

@Configuration
public class ModelCommands {
    Logger logger = LoggerFactory.getLogger(ModelCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    private Terminal terminal;

    public ModelCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter, Terminal terminal) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
        this.terminal = terminal;
    }

    @Bean
    CommandRegistration getModelsCommand() {
        return CommandRegistration
                .builder()
                .command("get-models")
                .description("Get list of models")
                .group("Model Commands")
                .withOption()
                    .longNames("printOption")
                    .description("compact|raw|pretty")
                    .defaultValue("pretty")
                    .and()
                .withTarget()
                    .function(ctx -> {
                        ResponseEntity<String> response = workflowClient.getModels().block();
                        terminal.writer().print(jsonFormatter.format(response.getBody(), ctx.getOptionValue("printOption")));
                        terminal.writer().println();
                        terminal.writer().flush();
                        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
                    })
                .and()
                .build();
    }

    @Bean
    CommandRegistration getModelCommand() {
        return CommandRegistration
                .builder()
                .command("get-model")
                .description("Get model")
                .group("Model Commands")
                .withOption()
                    .longNames("printOption")
                    .description("compact|raw|pretty")
                    .defaultValue("pretty")
                    .and()
                .withOption()
                    .longNames("modelId")
                    .description("ModelID of model to retrieve")
                    .arity(CommandRegistration.OptionArity.EXACTLY_ONE)
                    .position(0)
                    .type(String.class)
                    .required(true)
                    .and()
                .withTarget()
                    .function(ctx -> {
                        String modelId = ctx.getOptionValue("modelId");
                        ResponseEntity<String> response = workflowClient.getModel(modelId).block();
                        terminal.writer().print(jsonFormatter.format(response.getBody(), ctx.getOptionValue("printOption")));
                        terminal.writer().println();
                        terminal.writer().flush();
                        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
                    })
                .and()
                .build();
    }
}
