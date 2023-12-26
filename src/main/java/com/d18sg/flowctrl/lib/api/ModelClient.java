package com.d18sg.flowctrl.lib.api;

import reactor.core.publisher.Mono;

public interface ModelClient {

    Mono<String> getModels();

    Mono<String> getModel(String filter);

    Mono<String> createModel();

    Mono<String> updateModel(String modelId);

    Mono<String> deleteModel(String modelId);

    Mono<String> getModelEditorSource(String modelId);

    Mono<String> setModelEditorSource(String modelId, String modelEditorSource);

    Mono<String> getModelEditorSourceExtra(String modelId, String resourceId);

    Mono<String> setModelEditorSourceExtra(String modelId, String resourceId, String modelEditorSourceExtra);

    Mono<String> getModelResourceData(String modelId, String resourceId);
}
