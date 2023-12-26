package com.d18sg.flowctrl.lib.api;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import com.d18sg.flowctrl.lib.dto.ProcessDefinitionsDTO;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import reactor.core.publisher.Mono;

public interface ProcessDefinitionClient {

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITIONS)
    Mono<ProcessDefinitionsDTO> getProcessDefinitionsDTO();

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITIONS)
    Mono<String> getProcessDefinitions();

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> getProcessDefinition(String processDefinitionId);

    @PutExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> updateProcessDefinitionCategory(String processDefinitionId, String category);

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION_RESOURCES_DATA)
    Mono<String> getProcessDefinitionResourceData(String processDefinitionId);

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION_MODEL)
    Mono<String> getProcessDefinitionModel(String processDefinitionId);

    @PutExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> suspendProcessDefinition(String processDefinitionId);

    @PutExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> activateProcessDefinition(String processDefinitionId);

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION_CANDIDATE_STARTERS)
    Mono<String> getProcessDefinitionCandidateStarters(String processDefinitionId);

    @PostExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> addCandidateStarterUserToProcessDefinition(String processDefinitionId, String userId);

    @PostExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> addCandidateStarterGroupToProcessDefinition(String processDefinitionId, String groupId);

    @DeleteExchange(FlowableDefinitions.PROCESS_DEFINITION_CANDIDATE_STARTER)
    Mono<String> deleteCandidateStarterFromProcessDefinition(String processDefinitionId, String family, String identityId);

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION_CANDIDATE_STARTER)
    Mono<String> getCandidateStarterFromProcessDefinition(String processDefinitionId, String family, String identityId);
}
