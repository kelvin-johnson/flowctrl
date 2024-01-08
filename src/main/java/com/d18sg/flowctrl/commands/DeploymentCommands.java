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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

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
