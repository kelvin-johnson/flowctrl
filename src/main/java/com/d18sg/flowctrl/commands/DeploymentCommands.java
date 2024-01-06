package com.d18sg.flowctrl.commands;


import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Command(group = "Deployment Commands")
public class DeploymentCommands {

    Logger logger = LoggerFactory.getLogger(DeploymentCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public DeploymentCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }

    @Command(command = "get-deployments")
    public String get(
            @Option(description = "compact|raw|pretty", defaultValue = "pretty") String printOption
            , @Option(description = "Only return deployments with the given name") String name
            , @Option(description = "Only return deployments with a name like the given name") String nameLike
            , @Option(description = "Only return deployments with the given category") String category
            , @Option(description = "Only return deployments which don’t have the given category") String categoryNotEquals
            , @Option(description = "Only return deployments with the given tenantId") String tenantId
            , @Option(description = "Only return deployments with a tenantId like the given value") String tenantIdLike
            , @Option(description = "If true, only returns deployments without a tenantId set. If false, the withoutTenantId parameter is ignored") String withoutTenantId
            , @Option(description = "Property to sort on, to be used together with the 'order'. 'id' (default), 'name', 'deployTime' or 'tenantId'") String sort
    ) {
 
        Map<String, String> requestParameters = packageParameters(
                  new ImmutablePair<>("name", name)
                , new ImmutablePair<>("nameLike", nameLike)
                , new ImmutablePair<>("category", category)
                , new ImmutablePair<>("categoryNotEquals", categoryNotEquals)
                , new ImmutablePair<>("tenantId", tenantId)
                , new ImmutablePair<>("tenantIdLike", tenantIdLike)
                , new ImmutablePair<>("withoutTenantId", withoutTenantId)
                , new ImmutablePair<>("sort", sort)
        );

        String response = workflowClient.getDeployments(requestParameters).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    private Map<String, String> packageParameters(Pair<String, String>... parameters) {
        Map<String, String> requestParameters = new HashMap<>();
        Arrays.stream(parameters).toList().forEach(p -> {
            if(p.getValue() != null && !p.getValue().isBlank() && !p.getValue().isEmpty())
                requestParameters.put(p.getKey(), p.getValue());
        });
        return requestParameters;
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
    public String getResourceData(@Option(defaultValue = "PRETTY") String printOption, @Option String deploymentId, @Option String resourceId) {
        String response = workflowClient.getDeploymentResourceData(deploymentId, resourceId).block().toString();
        return response;
    }

}
