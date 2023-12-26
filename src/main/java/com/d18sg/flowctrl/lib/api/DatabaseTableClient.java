package com.d18sg.flowctrl.lib.api;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface DatabaseTableClient {

    @GetExchange(FlowableDefinitions.DATABASE_TABLES)
    Mono<String> getDatabaseTables();


}
