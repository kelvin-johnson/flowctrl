package com.d18sg.flowctrl.lib.api;

import reactor.core.publisher.Mono;

public interface JobClient {

    Mono<String> getJobs();

    Mono<String> getJob(String jobId);
}
