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

package com.d18sg.flowctrl.lib;

import com.d18sg.flowctrl.Settings;
import com.d18sg.flowctrl.WebClientWrapper;
import com.d18sg.flowctrl.lib.api.*;
import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import com.d18sg.flowctrl.lib.dto.ProcessDefinitionsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class WorkflowClient implements ProcessDefinitionClient, DeploymentClient, ModelClient, UserClient {//, DatabaseTableClient {

    private WebClientWrapper webClientWrapper;
    private Settings settings;

    private DatabaseTableClient databaseTableClient;
    private DeploymentClient deploymentClient;
    private EngineClient engineClient;
    private ExecutionClient executionClient;
    private FormClient formClient;
    private GroupClient groupClient;
    private HistoryClient historyClient;
    private JobClient jobClient;
    private ModelClient modelClient;
    private ProcessDefinitionClient processDefinitionClient;
    private ProcessInstanceClient processInstanceClient;
    private RuntimeClient runtimeClient;
    private TaskClient taskClient;
    private UserClient userClient;

    private ObjectMapper mapper = new ObjectMapper();

    public WorkflowClient(WebClientWrapper webClientWrapper, Settings settings) {
        this.webClientWrapper = webClientWrapper;
        this.settings = settings;
        initClients();
    }

    private void initClients() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClientWrapper.getWebClient())).build();

        databaseTableClient = httpServiceProxyFactory.createClient(DatabaseTableClient.class);
        deploymentClient = httpServiceProxyFactory.createClient(DeploymentClient.class);
        engineClient = httpServiceProxyFactory.createClient(EngineClient.class);
        executionClient = httpServiceProxyFactory.createClient(ExecutionClient.class);
        formClient = httpServiceProxyFactory.createClient(FormClient.class);
        groupClient = httpServiceProxyFactory.createClient(GroupClient.class);
        historyClient = httpServiceProxyFactory.createClient(HistoryClient.class);
        jobClient = httpServiceProxyFactory.createClient(JobClient.class);
        modelClient = httpServiceProxyFactory.createClient(ModelClient.class);
        processDefinitionClient = httpServiceProxyFactory.createClient(ProcessDefinitionClient.class);
        processInstanceClient = httpServiceProxyFactory.createClient(ProcessInstanceClient.class);
        runtimeClient = httpServiceProxyFactory.createClient(RuntimeClient.class);
        taskClient = httpServiceProxyFactory.createClient(TaskClient.class);
        userClient = httpServiceProxyFactory.createClient(UserClient.class);
    }

    // Database Table
    //@Override
    public Mono<String> getDatabaseTables() {
        return databaseTableClient.getDatabaseTables();
    }

    //@Override
    public Mono<String> getDatabaseTable(String tableName) {
        return databaseTableClient.getDatabaseTable(tableName);
    }

    //@Override
    public Mono<String> getDatabaseTableColumns(String tableName) {
        return databaseTableClient.getDatabaseTableColumns(tableName);
    }

    //@Override
    public Mono<String> getDatabaseTableData(String tableName) {
        return databaseTableClient.getDatabaseTableData(tableName);
    }

    // Process Definition
    @Override
    public Mono<ProcessDefinitionsDTO> getProcessDefinitionsDTO() {
        return processDefinitionClient.getProcessDefinitionsDTO();
    }

    public Mono<String> getProcessDefinitions() {
        return processDefinitionClient.getProcessDefinitions();
    }

    @Override
    public Mono<String> getProcessDefinition(String processDefinitionId) {
        return processDefinitionClient.getProcessDefinition(processDefinitionId);
    }

    @Override
    public Mono<String> updateProcessDefinitionCategory(String processDefinitionId, String category) {
        return processDefinitionClient.updateProcessDefinitionCategory(processDefinitionId, category);
    }

    @Override
    public Mono<String> getProcessDefinitionResourceData(String processDefinitionId) {
        return processDefinitionClient.getProcessDefinitionResourceData(processDefinitionId);
    }

    @Override
    public Mono<String> getProcessDefinitionModel(String processDefinitionId) {
        return processDefinitionClient.getProcessDefinitionModel(processDefinitionId);
    }

    @Override
    public Mono<String> suspendProcessDefinition(String processDefinitionId) {
        return processDefinitionClient.suspendProcessDefinition(processDefinitionId);
    }

    @Override
    public Mono<String> activateProcessDefinition(String processDefinitionId) {
        return processDefinitionClient.activateProcessDefinition(processDefinitionId);
    }

    @Override
    public Mono<String> getProcessDefinitionCandidateStarters(String processDefinitionId) {
        return processDefinitionClient.getProcessDefinitionCandidateStarters(processDefinitionId);
    }

    @Override
    public Mono<String> addCandidateStarterUserToProcessDefinition(String processDefinitionId, String userId) {
        return null;
    }

    @Override
    public Mono<String> addCandidateStarterGroupToProcessDefinition(String processDefinitionId, String groupId) {
        return null;
    }

    @Override
    public Mono<String> deleteCandidateStarterFromProcessDefinition(String processDefinitionId, String family, String identityId) {
        return null;
    }

    @Override
    public Mono<String> getCandidateStarterFromProcessDefinition(String processDefinitionId, String family, String identityId) {
        return null;
    }

    // Deployment
    @Override
    public Mono<ResponseEntity<String>> getDeployments(Map<String,String> requestParameters) {
        return deploymentClient.getDeployments(requestParameters);
    }

    @Override
    public Mono<String> createDeployment(FileSystemResource fileSystemResource, String tenantId) {
        return deploymentClient.createDeployment(fileSystemResource, tenantId);
    }

    @Override
    public Mono<String> createDeployment(FileSystemResource fileSystemResource) {
        return deploymentClient.createDeployment(fileSystemResource);
    }

    @Override
    public Mono<String> deleteDeployment(String deploymentId) {
        return deploymentClient.deleteDeployment(deploymentId);
    }

    @Override
    public Mono<String> getDeploymentResources(String deploymentId) {
        return deploymentClient.getDeploymentResources(deploymentId);
    }

    @Override
    public Mono<String> getDeploymentResource(String deploymentId, String resourceId) {
        return deploymentClient.getDeploymentResource(deploymentId, resourceId);
    }

    @Override
    public Flux<DataBuffer> getDeploymentResourceData(String deploymentId, String resourceId) {
        return deploymentClient.getDeploymentResourceData(deploymentId, resourceId);
    }

    //Model
    @Override
    public Mono<String> getModels() {
        return modelClient.getModels();
    }

    @Override
    public Mono<String> getModel(String modelId) {
        return modelClient.getModel(modelId);
    }

    @Override
    public Mono<String> createModel(String name, Map<String,String> modelInfo) {
        return modelClient.createModel(name, modelInfo);
    }

    @Override
    public Mono<String> updateModel(String modelId) {
        return null;
    }

    @Override
    public Mono<String> deleteModel(String modelId) {
        return modelClient.deleteModel(modelId);
    }

    @Override
    public Mono<String> getModelEditorSource(String modelId) {
        return modelClient.getModelEditorSource(modelId);
    }

    @Override
    public Mono<String> setModelEditorSource(String modelId, String modelEditorSource) {
        return null;
    }

    @Override
    public Mono<String> getModelEditorSourceExtra(String modelId, String resourceId) {
        return null;
    }

    @Override
    public Mono<String> setModelEditorSourceExtra(String modelId, String resourceId, String modelEditorSourceExtra) {
        return null;
    }

    //User
    @Override
    public Mono<ResponseEntity<String>> getUsers(Map<String,String> requestParameters) {
        return userClient.getUsers(requestParameters);
    }

    @Override
    public Mono<String> getUser(String userId) {
        return userClient.getUser(userId);
    }

    @Override
    public Mono<String> createUser(String userId, Map<String, String> requestParameters) {
        return null;
    }

    @Override
    public Mono<String> deleteUser(String userId) {
        return null;
    }

    @Override
    public Flux<DataBuffer> getUserPicture(String userId) {
        return userClient.getUserPicture(userId);
    }

    @Override
    public Mono<String> getUserInfoList(String userId) {
        return null;
    }

    @Override
    public Mono<String> getUserInfo(String userId, String key) {
        return null;
    }

    @Override
    public Mono<String> updateUserInfo(String userId, String key, Map<String, String> requestParameters) {
        return null;
    }

    @Override
    public Mono<String> createUserInfo(String userId, String key, Map<String, String> requestParameters) {
        return null;
    }

    @Override
    public Mono<String> deleteUserInfo(String userId, String key) {
        return userClient.deleteUserInfo(userId, key);
    }

    @Override
    public Flux<DataBuffer> getUserPictureData(String userId) {
        return userClient.getUserPictureData(userId);
    }

    @Override
    public Mono<ResponseEntity<String>> updateUserPicture(FileSystemResource fileSystemResource, String userId, String mimeType) {
        return userClient.updateUserPicture(fileSystemResource, userId, mimeType);
    }

    @Override
    public String willItWork() {
        return "Wow! It works!";
    }
}

//Mono<ResponseEntity<T>>