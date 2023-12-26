package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DatabaseTableCommands {
    Logger logger = LoggerFactory.getLogger(DatabaseTableCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public DatabaseTableCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }

    @ShellMethod(key = "get-database-tables")
    public String get(@Option(defaultValue = "PRETTY") String printOption) {
        String response = workflowClient.getDatabaseTables().block().toString();
        return jsonFormatter.format(response, printOption);
    }
}
