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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command(group = "Model Commands")
public class ModelCommands {
    Logger logger = LoggerFactory.getLogger(ModelCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public ModelCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }

    @Command(command = "get-models")
    public String get(@Option(defaultValue = "PRETTY") String printOption) {
        String response = workflowClient.getModels().block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @Command(command = "get-model")
    public String get(@Option(defaultValue = "PRETTY") String printOption, @Option(description = "ModelID of model to retrieve") String modelId) {
        String response = workflowClient.getModel(modelId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

}
