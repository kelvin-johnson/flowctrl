package com.d18sg.flowctrl.lib.api;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface DeploymentClient {

    @GetExchange(FlowableDefinitions.DEPLOYMENTS)
    Mono<String> getDeployments();

    @PostExchange(value = FlowableDefinitions.DEPLOYMENT, contentType = "multipart/form-data")
    Mono<String> createDeployment(String fileName, String tenantId);

    @PostExchange(value = FlowableDefinitions.DEPLOYMENT, contentType = "multipart/form-data")
    Mono<String> createDeployment(String fileName);

    @DeleteExchange(FlowableDefinitions.DEPLOYMENT)
    Mono<String> deleteDeployment(String deploymentId);

    @GetExchange(FlowableDefinitions.DEPLOYMENT_RESOURCES)
    Mono<String> getDeploymentResources(String deploymentId);

    @GetExchange(FlowableDefinitions.DEPLOYMENT_RESOURCE)
    Mono<String> getDeploymentResource(String deploymentId, String resourceId);

    @GetExchange(FlowableDefinitions.DEPLOYMENT_RESOURCE_DATA)
    Mono<String> getDeploymentResourceData(String deploymentId, String resourceId);
}
