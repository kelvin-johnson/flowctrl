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
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

//@Command(group = "Database Table Commands")
@Configuration
public class DatabaseTableCommands {
    Logger logger = LoggerFactory.getLogger(DatabaseTableCommands.class);
    private WorkflowClient workflowClient;

    private Terminal terminal;

    private JsonFormatter jsonFormatter;

    public DatabaseTableCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter, Terminal terminal) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
        this.terminal = terminal;
    }

    //@Command(command = "get-database-tables", description = "Get list of database tables")
    private String getDatabaseTables(String printOption) {
        ResponseEntity<String> response = workflowClient.getDatabaseTables().block();
        return jsonFormatter.format(response.getBody(), printOption);
    }

    @Bean
    CommandRegistration getDatabaseTablesCommand() {
        return CommandRegistration
                .builder()
                .command("get-database-tables")
                .description("Get list of database tables")
                .withOption()
                    .longNames("printOption")
                    .description("compact|raw|pretty")
                    .defaultValue("pretty")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withTarget()
                    .function(ctx -> {
                        String printOption = ctx.getOptionValue("printOption");
                        ResponseEntity<String> response = workflowClient.getDatabaseTables().block();

                        terminal.writer().print(jsonFormatter.format(response.getBody(), printOption));
                        terminal.writer().println();
                        terminal.writer().flush();
                        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
                    })
                .and()
                .build();
    }



    //@Command(command = "get-database-table", description = "Get database table")
    //private String getDatabaseTable(@Option(defaultValue = "PRETTY") String printOption, @Option String tableName) {
    private Integer getDatabaseTable(String printOption, String tableName) {
        ResponseEntity<String> response = workflowClient.getDatabaseTable(tableName).block();

        terminal.writer().print(jsonFormatter.format(response.getBody(), printOption));
        terminal.writer().println();
        terminal.writer().flush();
        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
    }

    @Bean
    CommandRegistration getDatabaseTableCommand() {
        return CommandRegistration
                .builder()
                .command("get-database-table")
                .description("Get database table")
                .withOption()
                    .longNames("printOption")
                    .description("compact|raw|pretty")
                    .defaultValue("pretty")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("tableName")
                    .description("Name of table")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.EXACTLY_ONE)
                    .position(0)
                    .and()
                .withTarget()
                    .function(ctx -> this.getDatabaseTable(ctx.getOptionValue("printOption"), ctx.getOptionValue("tableName"))
                    )
                .and()
                .build();
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
