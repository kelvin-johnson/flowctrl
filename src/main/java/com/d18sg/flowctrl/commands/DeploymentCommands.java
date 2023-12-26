package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DeploymentCommands {

    Logger logger = LoggerFactory.getLogger(DeploymentCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public DeploymentCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }

    @ShellMethod(key = "get-deployments")
    public String get(@Option(defaultValue = "PRETTY") String printOption) {
        String response = workflowClient.getDeployments().block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @ShellMethod(key = "create-deployment")
    public String create(@Option(defaultValue = "PRETTY") String printOption, String fileName, @Option(defaultValue="") String tenantId) {
        String response = workflowClient.createDeployment(fileName).block().toString();
        //String response = workflowClient.createDeployment(fileName, tenantId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @ShellMethod(key = "delete-deployment")
    public String delete(@Option(defaultValue = "PRETTY") String printOption, @Option(description = "Delete a deployment") String deploymentId) {
        String response = workflowClient.deleteDeployment(deploymentId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @ShellMethod(key = "get-deployment-resources")
    public String getResources(@Option(defaultValue = "PRETTY") String printOption, @Option(description = "Get deployment resources") String deploymentId) {
        String response = workflowClient.getDeploymentResources(deploymentId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @ShellMethod(key = "get-deployment-resource")
    public String getResource(@Option(defaultValue = "PRETTY") String printOption, @Option(description = "Get deployment resource") String deploymentId, @Option(description = "Get deployment resource") String resourceId) {
        String response = workflowClient.getDeploymentResource(deploymentId, resourceId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @ShellMethod(key = "get-deployment-resource-data")
    public String getResourceData(@Option(defaultValue = "PRETTY") String printOption, @Option(description = "Get deployment resource data") String deploymentId, @Option(description = "Get deployment resource data") String resourceId) {
        String response = workflowClient.getDeploymentResourceData(deploymentId, resourceId).block().toString();
        return response;
    }

}
