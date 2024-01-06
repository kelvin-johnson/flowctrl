package com.d18sg.flowctrl.lib.api;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface DatabaseTableClientGeneric <T> {

    T getDatabaseTables();

    T getDatabaseTable(String tableName);

    T getDatabaseTableColumns(String tableName);

    T getDatabaseTableData(String tableName);
}
