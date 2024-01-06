package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Command;

@Command(group = "Form Commands")
public class GroupCommands {
    Logger logger = LoggerFactory.getLogger(GroupCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public GroupCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }
}
