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

import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command(group = "Process Definition Commands")
public class ProcessDefinitionCommands {
    Logger logger = LoggerFactory.getLogger(ProcessDefinitionCommands.class);
    private WorkflowClient workflowClient;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonFormatter jsonFormatter;

    public ProcessDefinitionCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }

    @Command(command = "get-process-definitions")
    public String get(@Option(defaultValue = "PRETTY") String printOption) {
        String response = workflowClient.getProcessDefinitions().block().toString();
        return jsonFormatter.format(response, printOption);
    }

}
