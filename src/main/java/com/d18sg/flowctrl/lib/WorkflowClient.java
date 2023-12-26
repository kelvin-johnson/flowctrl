package com.d18sg.flowctrl.lib;

import com.d18sg.flowctrl.Settings;
import com.d18sg.flowctrl.WebClientWrapper;
import com.d18sg.flowctrl.lib.api.*;
import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import com.d18sg.flowctrl.lib.dto.ProcessDefinitionsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

public class WorkflowClient implements ProcessDefinitionClient, DeploymentClient, ModelClient, DatabaseTableClient {

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
    @Override
    public Mono<String> getDatabaseTables() {
        return databaseTableClient.getDatabaseTables();
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
    public Mono<String> getDeployments() {
        return deploymentClient.getDeployments();
    }

    @Override
    public Mono<String> createDeployment(String fileName, String tenantId) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        FileSystemResource fileSystemResource = new FileSystemResource(fileName);
        builder.part(fileSystemResource.getFilename(), fileSystemResource);
        if(!tenantId.isBlank() && !tenantId.isEmpty()) {
            builder.part("tenantId", tenantId);
        }
        return this.webClientWrapper.getWebClient()
                .post()
                .uri(FlowableDefinitions.DEPLOYMENTS)
                .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> createDeployment(String fileName) {
        return deploymentClient.createDeployment(fileName);
        //return createDeployment(fileName, "");
    }



    @Override
    public Mono<String> deleteDeployment(String deploymentId) {
        return this.webClientWrapper.getWebClient()
                .delete()
                .uri(FlowableDefinitions.DEPLOYMENT, deploymentId)
                .retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> getDeploymentResources(String deploymentId) {
        return this.webClientWrapper.getWebClient()
                .get()
                .uri(FlowableDefinitions.DEPLOYMENT_RESOURCES, deploymentId)
                .retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> getDeploymentResource(String deploymentId, String resourceId) {
        return this.webClientWrapper.getWebClient()
                .get()
                .uri(FlowableDefinitions.DEPLOYMENT_RESOURCE, deploymentId, resourceId)
                .retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> getDeploymentResourceData(String deploymentId, String resourceId) {
        //This will return binary data. Need option to save to file or uuencode or something
        return this.webClientWrapper.getWebClient()
                .get()
                .uri( FlowableDefinitions.DEPLOYMENT_RESOURCE_DATA, deploymentId, resourceId)
                .retrieve().bodyToMono(String.class);
    }

    //Model


    @Override
    public Mono<String> getModels() {
        return this.webClientWrapper.getWebClient()
                .get()
                .uri(FlowableDefinitions.MODELS)
                .retrieve().bodyToMono(String.class);
    }


    @Override
    public Mono<String> getModel(String modelId) {
        return this.webClientWrapper.getWebClient()
                .get()
                .uri(FlowableDefinitions.MODEL, modelId)
                .retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> createModel() {
        return null;
    }

    @Override
    public Mono<String> updateModel(String modelId) {
        return null;
    }

    @Override
    public Mono<String> deleteModel(String modelId) {
        return null;
    }

    @Override
    public Mono<String> getModelEditorSource(String modelId) {
        return null;
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

    @Override
    public Mono<String> getModelResourceData(String modelId, String resourceId) {
        return null;
    }
}