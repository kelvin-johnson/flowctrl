package com.d18sg.flowctrl.lib.api;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface DeploymentClient {

    @GetExchange(FlowableDefinitions.DEPLOYMENTS)
    Mono<String> getDeployments(@RequestParam Map<String,String> requestParameters);

    @PostExchange(value = FlowableDefinitions.DEPLOYMENTS, contentType = "multipart/form-data")
    Mono<String> createDeployment(@RequestPart FileSystemResource fileSystemResource, @RequestPart String tenantId);

    @PostExchange(value = FlowableDefinitions.DEPLOYMENTS, contentType = "multipart/form-data")
    Mono<String> createDeployment(@RequestPart FileSystemResource fileSystemResource);

    @DeleteExchange(FlowableDefinitions.DEPLOYMENT)
    Mono<String> deleteDeployment(@PathVariable String deploymentId);

    @GetExchange(FlowableDefinitions.DEPLOYMENT_RESOURCES)
    Mono<String> getDeploymentResources(@PathVariable String deploymentId);

    @GetExchange(FlowableDefinitions.DEPLOYMENT_RESOURCE)
    Mono<String> getDeploymentResource(@PathVariable String deploymentId, @PathVariable String resourceId);

    @GetExchange(FlowableDefinitions.DEPLOYMENT_RESOURCE_DATA)
    Mono<String> getDeploymentResourceData(@PathVariable String deploymentId, @PathVariable String resourceId);
}
