package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;


@ShellComponent
public class ExecutionCommands {
    Logger logger = LoggerFactory.getLogger(ExecutionCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public ExecutionCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }
}