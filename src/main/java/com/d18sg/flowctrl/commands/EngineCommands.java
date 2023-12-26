package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;


@ShellComponent
public class EngineCommands {
    Logger logger = LoggerFactory.getLogger(EngineCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public EngineCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }
}
