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

import com.codernaught.wafle.ParameterPackager;
import com.codernaught.wafle.WorkflowClient;
import com.codernaught.wafle.definition.FlowableDefinitions;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jline.terminal.Terminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.CommandRegistration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeploymentCommandsProgrammatic {

    Logger logger = LoggerFactory.getLogger(DeploymentCommandsProgrammatic.class);
    private WorkflowClient workflowClient;

    private Terminal terminal;

    private JsonFormatter jsonFormatter;

    public DeploymentCommandsProgrammatic(WorkflowClient workflowClient, JsonFormatter jsonFormatter, Terminal terminal) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
        this.terminal = terminal;
    }

    @Bean
    CommandRegistration getDeployments() {
        return CommandRegistration.builder()
                .command("get-deployments-p")
                .withHelpOptions()
                    .enabled(true)
                    .longNames("help")
                    .shortNames('h')
                    .command("help")
                    .and()
                .withOption()
                    .longNames("printOption")
                    .description("compact|raw|pretty")
                    .defaultValue("pretty")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("name")
                    .description("Only return deployments with the given name")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("nameLike")
                    .description("Only return deployments with a name like the given name")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("category")
                    .description("Only return deployments with the given category")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("categoryNotEquals")
                    .description("Only return deployments which don’t have the given category")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("tenantId")
                    .description("Only return deployments with the given tenantId")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("tenantIdLike")
                    .description("Only return deployments with a tenantId like the given value")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("withoutTenantId")
                    .description("Only return deployments without a tenantId")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("sort")
                    .description("Property to sort results on, should be one of id, name, deployTime or tenantId")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("order")
                    .description("Sort order, to be used in conjunction with the 'sort' parameter. Values may be 'asc' or 'desc'")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("start")
                    .description("Parameter to allow for paging of the result. Default: 0")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("size")
                    .description("Parameter to allow for paging of the result. Default: 10")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withTarget()
                    .function(ctx -> {

                        Map<String, String> requestParameters = new HashMap<>();
                        ctx.getParserResults().results().forEach(e -> {
                            //requestParameters.put(k, Arrays.toString(v));
                            logger.info("Option first long name: " + e.option().getLongNames()[0]);
                            logger.info("Option value: " + e.value());
                        });

                        requestParameters = ParameterPackager.packageParameters(
                                new ImmutablePair<>(FlowableDefinitions.NAME, ctx.getOptionValue("name"))
                                , new ImmutablePair<>(FlowableDefinitions.NAME_LIKE, ctx.getOptionValue("nameLike"))
                                , new ImmutablePair<>(FlowableDefinitions.CATEGORY, ctx.getOptionValue("category"))
                                , new ImmutablePair<>(FlowableDefinitions.CATEGORY_NOT_EQUALS, ctx.getOptionValue("categoryNotEquals"))
                                , new ImmutablePair<>(FlowableDefinitions.TENANT_ID, ctx.getOptionValue("tenantId"))
                                , new ImmutablePair<>(FlowableDefinitions.TENANT_ID_LIKE, ctx.getOptionValue("tenantIdLike"))
                                , new ImmutablePair<>(FlowableDefinitions.WITHOUT_TENANT_ID, ctx.getOptionValue("withoutTenantId"))
                                , new ImmutablePair<>(FlowableDefinitions.SORT, ctx.getOptionValue("sort"))
                                , new ImmutablePair<>(FlowableDefinitions.START, ctx.getOptionValue("start"))
                                , new ImmutablePair<>(FlowableDefinitions.SIZE, ctx.getOptionValue("size"))
                        );

                        ResponseEntity<String> response = workflowClient.getDeployments(requestParameters).block();
                        terminal.writer().print(jsonFormatter.format(response.getBody(), ctx.getOptionValue("printOption")));
                        terminal.writer().println();
                        terminal.writer().flush();
                        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());

                })
                .and()
                .build();
    }

    @Bean
    CommandRegistration createDeployment() {
        return CommandRegistration.builder()
                .command("create-deployment-p")
                .withHelpOptions()
                    .enabled(true)
                    .longNames("help")
                    .shortNames('h')
                    .command("help")
                    .and()
                .withOption()
                    .longNames("printOption")
                    .description("compact|raw|pretty")
                    .defaultValue("pretty")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withOption()
                    .longNames("fileName")
                    .description("The name of the file to deploy")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.EXACTLY_ONE)
                    .position(0)
                    .required()
                    .and()
                .withOption()
                    .longNames("tenantId")
                    .description("The tenantId to deploy the file to")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                    .and()
                .withTarget()
                    .function(ctx -> {
                        ResponseEntity<String> response;
                        if (ctx.hasMappedOption("tenantId")) {
                            response = workflowClient.createDeployment(ctx.getOptionValue("fileName"), ctx.getOptionValue("tenantId")).block();
                        } else {
                            response = workflowClient.createDeployment(ctx.getOptionValue("fileName")).block();
                        }

                        terminal.writer().print(jsonFormatter.format(response.getBody(), ctx.getOptionValue("printOption")));
                        terminal.writer().println();
                        terminal.writer().flush();
                        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
                })
                .and()
                .build();
    }


}
