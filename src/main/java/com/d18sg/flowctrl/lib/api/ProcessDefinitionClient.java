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
import com.d18sg.flowctrl.lib.dto.ProcessDefinitionsDTO;
import org.springframework.web.bind.annotation.PathVariable;
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
    Mono<String> getProcessDefinition(@PathVariable String processDefinitionId);

    @PutExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> updateProcessDefinitionCategory(@PathVariable String processDefinitionId, String category);

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION_RESOURCES_DATA)
    Mono<String> getProcessDefinitionResourceData(@PathVariable String processDefinitionId);

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION_MODEL)
    Mono<String> getProcessDefinitionModel(@PathVariable String processDefinitionId);

    @PutExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> suspendProcessDefinition(@PathVariable String processDefinitionId);

    @PutExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> activateProcessDefinition(@PathVariable String processDefinitionId);

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION_CANDIDATE_STARTERS)
    Mono<String> getProcessDefinitionCandidateStarters(@PathVariable String processDefinitionId);

    @PostExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> addCandidateStarterUserToProcessDefinition(@PathVariable String processDefinitionId, String userId);

    @PostExchange(FlowableDefinitions.PROCESS_DEFINITION)
    Mono<String> addCandidateStarterGroupToProcessDefinition(@PathVariable String processDefinitionId, String groupId);

    @DeleteExchange(FlowableDefinitions.PROCESS_DEFINITION_CANDIDATE_STARTER)
    Mono<String> deleteCandidateStarterFromProcessDefinition(@PathVariable String processDefinitionId, @PathVariable String family, @PathVariable String identityId);

    @GetExchange(FlowableDefinitions.PROCESS_DEFINITION_CANDIDATE_STARTER)
    Mono<String> getCandidateStarterFromProcessDefinition(@PathVariable String processDefinitionId, @PathVariable String family, @PathVariable String identityId);
}
