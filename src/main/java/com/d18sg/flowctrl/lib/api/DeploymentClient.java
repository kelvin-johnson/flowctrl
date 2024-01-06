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

package com.d18sg.flowctrl.lib.api;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface DeploymentClient {

    @GetExchange(FlowableDefinitions.DEPLOYMENTS)
    Mono<ResponseEntity<String>> getDeployments(@RequestParam Map<String,String> requestParameters);

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
    Flux<DataBuffer> getDeploymentResourceData(@PathVariable String deploymentId, @PathVariable String resourceId);
}
