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
