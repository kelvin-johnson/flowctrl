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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Command(group = "Deployment Commands")
public class DeploymentCommands {

    Logger logger = LoggerFactory.getLogger(DeploymentCommands.class);
    private WorkflowClient workflowClient;

    private Terminal terminal;

    private JsonFormatter jsonFormatter;

    public DeploymentCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter, Terminal terminal) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
        this.terminal = terminal;
    }
/*

    @Command(command = "get-deployments")
    public Integer get(
            @Option(description = "compact|raw|pretty", defaultValue = "pretty") String printOption
            , @Option(description = "Only return deployments with the given name") String name
            , @Option(description = "Only return deployments with a name like the given name") String nameLike
            , @Option(description = "Only return deployments with the given category") String category
            , @Option(description = "Only return deployments which don’t have the given category") String categoryNotEquals
            , @Option(description = "Only return deployments with the given tenantId") String tenantId
            , @Option(description = "Only return deployments with a tenantId like the given value") String tenantIdLike
            , @Option(description = "If true, only returns deployments without a tenantId set. If false, the withoutTenantId parameter is ignored") String withoutTenantId
            , @Option(description = "Property to sort on, to be used together with the 'order'. 'id' (default), 'name', 'deployTime' or 'tenantId'") String sort
            , @Option(description = "Sort order, to be used in conjunction with the 'sort' parameter. Values may be 'asc' or 'desc'") String order
            , @Option(description = "Parameter to allow for paging of the result. Default: 0") String start
            , @Option(description = "Parameter to allow for paging of the result. Default: 10") String size
    ) {
        Map<String, String> requestParameters = ParameterPackager.packageParameters(
                  new ImmutablePair<>(FlowableDefinitions.NAME, name)
                , new ImmutablePair<>(FlowableDefinitions.NAME_LIKE, nameLike)
                , new ImmutablePair<>(FlowableDefinitions.CATEGORY, category)
                , new ImmutablePair<>(FlowableDefinitions.CATEGORY_NOT_EQUALS, categoryNotEquals)
                , new ImmutablePair<>(FlowableDefinitions.TENANT_ID, tenantId)
                , new ImmutablePair<>(FlowableDefinitions.TENANT_ID_LIKE, tenantIdLike)
                , new ImmutablePair<>(FlowableDefinitions.WITHOUT_TENANT_ID, withoutTenantId)
                , new ImmutablePair<>(FlowableDefinitions.SORT, sort)
                , new ImmutablePair<>(FlowableDefinitions.START, start)
                , new ImmutablePair<>(FlowableDefinitions.SIZE, size)
        );

        ResponseEntity<String> response = workflowClient.getDeployments(requestParameters).block();
        terminal.writer().print(jsonFormatter.format(response.getBody(), printOption));
        terminal.writer().println();
        terminal.writer().flush();
        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
    }

    @Command(command = "create-deployment")
    public Integer create(@Option(description = "compact|raw|pretty", defaultValue = "pretty") String printOption
            ,@Option String fileName
            ,@Option String tenantId) {
        FileSystemResource fileSystemResource = new FileSystemResource(fileName);
        ResponseEntity<String> response = workflowClient.createDeployment(fileSystemResource, tenantId).block();
        terminal.writer().print(jsonFormatter.format(response.getBody(), printOption));
        terminal.writer().println();
        terminal.writer().flush();
        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
    }
*/

    @Bean
    CommandRegistration getDeployments() {
        return CommandRegistration.builder()
                .command("get-deployments")
                .description("Get list of deployments")
                .group("Deployment Commands")
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
                .longNames(FlowableDefinitions.NAME)
                .description("Only return deployments with the given name")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.NAME_LIKE)
                .description("Only return deployments with a name like the given name")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.CATEGORY)
                .description("Only return deployments with the given category")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.CATEGORY_NOT_EQUALS)
                .description("Only return deployments which don’t have the given category")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.TENANT_ID)
                .description("Only return deployments with the given tenantId")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.TENANT_ID_LIKE)
                .description("Only return deployments with a tenantId like the given value")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.WITHOUT_TENANT_ID)
                .description("Only return deployments without a tenantId")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.SORT)
                .description("Property to sort results on, should be one of id, name, deployTime or tenantId")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.ORDER)
                .description("Sort order, to be used in conjunction with the 'sort' parameter. Values may be 'asc' or 'desc'")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.START)
                .description("Parameter to allow for paging of the result. Default: 0")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.ZERO_OR_ONE)
                .and()
                .withOption()
                .longNames(FlowableDefinitions.SIZE)
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
                            new ImmutablePair<>(FlowableDefinitions.NAME, ctx.getOptionValue(FlowableDefinitions.NAME))
                            , new ImmutablePair<>(FlowableDefinitions.NAME_LIKE, ctx.getOptionValue(FlowableDefinitions.NAME_LIKE))
                            , new ImmutablePair<>(FlowableDefinitions.CATEGORY, ctx.getOptionValue(FlowableDefinitions.CATEGORY))
                            , new ImmutablePair<>(FlowableDefinitions.CATEGORY_NOT_EQUALS, ctx.getOptionValue(FlowableDefinitions.CATEGORY_NOT_EQUALS))
                            , new ImmutablePair<>(FlowableDefinitions.TENANT_ID, ctx.getOptionValue(FlowableDefinitions.TENANT_ID))
                            , new ImmutablePair<>(FlowableDefinitions.TENANT_ID_LIKE, ctx.getOptionValue(FlowableDefinitions.TENANT_ID_LIKE))
                            , new ImmutablePair<>(FlowableDefinitions.WITHOUT_TENANT_ID, ctx.getOptionValue(FlowableDefinitions.WITHOUT_TENANT_ID))
                            , new ImmutablePair<>(FlowableDefinitions.ORDER, ctx.getOptionValue(FlowableDefinitions.ORDER))
                            , new ImmutablePair<>(FlowableDefinitions.SORT, ctx.getOptionValue(FlowableDefinitions.SORT))
                            , new ImmutablePair<>(FlowableDefinitions.START, ctx.getOptionValue(FlowableDefinitions.START))
                            , new ImmutablePair<>(FlowableDefinitions.SIZE, ctx.getOptionValue(FlowableDefinitions.SIZE))
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
    CommandRegistration getDeployment() {
        return CommandRegistration.builder()
                .command("get-deployment")
                .description("Get deployment")
                .group("Deployment Commands")
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
                .longNames("deploymentId")
                .description("The id of the deployment to get")
                .type(String.class)
                .arity(CommandRegistration.OptionArity.EXACTLY_ONE)
                .position(0)
                .required()
                .and()
                .withTarget()
                .function(ctx -> {
                    ResponseEntity<String> response = workflowClient.getDeployment(ctx.getOptionValue("deploymentId")).block();
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
                .command("create-deployment")
                .description("Create deployment")
                .group("Deployment Commands")
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


    @Command(command = "delete-deployment")
    public Integer delete(@Option(description = "compact|raw|pretty", defaultValue = "pretty") String printOption, @Option String deploymentId) {
        ResponseEntity<Void> response = workflowClient.deleteDeployment(deploymentId).block();
        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
    }

    @Command(command = "get-deployment-resources")
    public Integer getResources(@Option(defaultValue = "pretty") String printOption, @Option String deploymentId) {
        ResponseEntity<String> response = workflowClient.getDeploymentResources(deploymentId).block();
        terminal.writer().print(jsonFormatter.format(response.getBody(), printOption));
        terminal.writer().println();
        terminal.writer().flush();
        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
    }

    @Command(command = "get-deployment-resource")
    public Integer getResource(@Option(defaultValue = "PRETTY") String printOption, @Option String deploymentId, @Option String resourceId) {
        ResponseEntity<String> response = workflowClient.getDeploymentResource(deploymentId, resourceId).block();
        terminal.writer().print(jsonFormatter.format(response.getBody(), printOption));
        terminal.writer().println();
        terminal.writer().flush();
        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 0 : response.getStatusCode().value());
    }

    @Command(command = "get-deployment-resource-data")
    public Integer getResourceData(@Option(defaultValue = "pretty") String printOption
            , @Option String deploymentId
            , @Option String resourceId
            , @Option String destinationFile
    )  {

        ResponseEntity<Flux<DataBuffer>> response = workflowClient.getDeploymentResourceData(deploymentId, resourceId).block();
        HttpStatusCode statusCode = response.getStatusCode();
        try {
            terminal.writer().println("Downloading " + resourceId + " from " + deploymentId + " to " + destinationFile);
            terminal.writer().flush();

            if (statusCode == HttpStatusCode.valueOf(200)) {
                Path path = Paths.get(destinationFile);
                DataBufferUtils.write(response.getBody(), path).block();
                terminal.writer().println(Files.size(path) + " bytes written to " + destinationFile);
                terminal.writer().flush();

            } else {
                terminal.writer().println("Error downloading " + resourceId + " from " + deploymentId + " to " + destinationFile);
                terminal.writer().flush();
            }

        } catch (IOException e) {
            terminal.writer().println("Error downloading " + resourceId + " from " + deploymentId + " to " + destinationFile);
        } finally {
            terminal.writer().flush();
            return (statusCode == HttpStatusCode.valueOf(200) ? 0 : statusCode.value());
        }
    }
}
