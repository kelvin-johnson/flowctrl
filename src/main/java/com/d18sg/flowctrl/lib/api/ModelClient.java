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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ModelClient {

    @GetExchange(FlowableDefinitions.MODELS)
    Mono<String> getModels();

    @GetExchange(FlowableDefinitions.MODEL)
    Mono<String> getModel(@PathVariable String modelId);

    @PostExchange(FlowableDefinitions.MODELS)
    Mono<String> createModel(@PathVariable String name, Map<String,String> modelInfo);

    Mono<String> updateModel(@PathVariable String modelId);

    @DeleteExchange(FlowableDefinitions.MODEL)
    Mono<String> deleteModel(@PathVariable String modelId);

    @GetExchange(FlowableDefinitions.MODEL_SOURCE)
    Mono<String> getModelEditorSource(@PathVariable String modelId);

    Mono<String> setModelEditorSource(@PathVariable String modelId, String modelEditorSource);

    @GetExchange(FlowableDefinitions.MODEL_SOURCE_EXTRA)
    Mono<String> getModelEditorSourceExtra(@PathVariable String modelId, @PathVariable String resourceId);

    Mono<String> setModelEditorSourceExtra(@PathVariable String modelId, @PathVariable String resourceId, String modelEditorSourceExtra);

}
