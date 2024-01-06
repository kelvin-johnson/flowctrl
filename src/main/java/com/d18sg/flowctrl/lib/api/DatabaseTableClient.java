package com.d18sg.flowctrl.lib.api;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface DatabaseTableClient extends DatabaseTableClientGeneric<Mono<String>> {
    @GetExchange(FlowableDefinitions.DATABASE_TABLES)
    Mono<String> getDatabaseTables();

    @GetExchange(FlowableDefinitions.DATABASE_TABLE)
    Mono<String> getDatabaseTable(@PathVariable String tableName);

    @GetExchange(FlowableDefinitions.DATABASE_COLUMN_FOR_SINGLE_TABLE)
    Mono<String> getDatabaseTableColumns(@PathVariable String tableName);

    @GetExchange(FlowableDefinitions.DATABASE_DATA_FOR_SINGLE_ROW)
    Mono<String> getDatabaseTableData(@PathVariable String tableName);
}
