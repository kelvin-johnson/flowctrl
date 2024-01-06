package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Command;

@Command(group = "History Commands")
public class HistoryCommands {
    Logger logger = LoggerFactory.getLogger(DatabaseTableCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public HistoryCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }
}
