package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command(group = "Database Table Commands")
public class DatabaseTableCommands {
    Logger logger = LoggerFactory.getLogger(DatabaseTableCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public DatabaseTableCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }

    @Command(command = "get-database-tables", description = "Get list of database tables")
    public String getDatabaseTables() {
        String response = workflowClient.getDatabaseTables().block().toString();
        return jsonFormatter.format(response, "pretty");
    }

    @Command(command = "get-database-table", description = "Get database table")
    public String getDatabaseTable(@Option(defaultValue = "PRETTY") String printOption, @Option String tableName) {
        String response = workflowClient.getDatabaseTable(tableName).block().toString();
        return jsonFormatter.format(response, printOption);
    }


    @Command(command = "get-database-columns", description = "Get database table columns")
    public String getDatabaseTableColumns(@Option(defaultValue = "PRETTY") String printOption, @Option(required = true) String tableName) {
        String response = workflowClient.getDatabaseTableColumns(tableName).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @Command(command = "get-database-data", description = "Get database table data")
    public String getDatabaseTableData(@Option(defaultValue = "PRETTY") String printOption, @Option String tableName) {
        String response = workflowClient.getDatabaseTableData(tableName).block().toString();
        return jsonFormatter.format(response, printOption);
    }

}
