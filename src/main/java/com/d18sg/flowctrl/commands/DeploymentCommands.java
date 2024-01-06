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

import com.d18sg.flowctrl.lib.ParameterPackager;
import com.d18sg.flowctrl.lib.WorkflowClient;
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
                  new ImmutablePair<>("name", name)
                , new ImmutablePair<>("nameLike", nameLike)
                , new ImmutablePair<>("category", category)
                , new ImmutablePair<>("categoryNotEquals", categoryNotEquals)
                , new ImmutablePair<>("tenantId", tenantId)
                , new ImmutablePair<>("tenantIdLike", tenantIdLike)
                , new ImmutablePair<>("withoutTenantId", withoutTenantId)
                , new ImmutablePair<>("sort", sort)
                , new ImmutablePair<>("start", sort)
                , new ImmutablePair<>("size", sort)
        );

        ResponseEntity<String> response = workflowClient.getDeployments(requestParameters).block();
        terminal.writer().print(jsonFormatter.format(response.getBody(), printOption));
        terminal.writer().println();
        terminal.writer().flush();
        return (response.getStatusCode() == HttpStatusCode.valueOf(200) ? 77 : response.getStatusCode().value());
    }

    @Command(command = "create-deployment")
    public String create(String fileName, @Option String tenantId) {
        FileSystemResource fileSystemResource = new FileSystemResource(fileName);
        String response = workflowClient.createDeployment(fileSystemResource, tenantId).block().toString();
        return jsonFormatter.format(response, "pretty");
    }

    @Command(command = "delete-deployment")
    public String delete(@Option String deploymentId) {
        String response = workflowClient.deleteDeployment(deploymentId).block().toString();
        return jsonFormatter.format(response, "pretty");
    }

    @Command(command = "get-deployment-resources")
    public String getResources(@Option(defaultValue = "PRETTY") String printOption, @Option String deploymentId) {
        String response = workflowClient.getDeploymentResources(deploymentId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @Command(command = "get-deployment-resource")
    public String getResource(@Option(defaultValue = "PRETTY") String printOption, @Option String deploymentId, @Option String resourceId) {
        String response = workflowClient.getDeploymentResource(deploymentId, resourceId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @Command(command = "get-deployment-resource-data")
    public String getResourceData(@Option(defaultValue = "PRETTY") String printOption
            , @Option String deploymentId
            , @Option String resourceId
            , @Option String destinationFile
    ) throws IOException {

        Flux<DataBuffer> flux = workflowClient.getDeploymentResourceData(deploymentId, resourceId);

        Path path = Paths.get(destinationFile);
        DataBufferUtils.write(flux, path).block();
        return Files.size(path) + " bytes written to " + destinationFile;
    }

/*
    private Map<String, String> packageParameters(Pair<String, String>... parameters) {
        Map<String, String> requestParameters = new HashMap<>();
        Arrays.stream(parameters).toList().forEach(p -> {
            if(p.getValue() != null && !p.getValue().isBlank() && !p.getValue().isEmpty())
                requestParameters.put(p.getKey(), p.getValue());
        });
        return requestParameters;
    }
    */
}
