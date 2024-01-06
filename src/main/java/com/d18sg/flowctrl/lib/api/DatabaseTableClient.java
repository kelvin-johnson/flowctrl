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
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface DatabaseTableClient {
    @GetExchange(FlowableDefinitions.DATABASE_TABLES)
    Mono<String> getDatabaseTables();

    @GetExchange(FlowableDefinitions.DATABASE_TABLE)
    Mono<String> getDatabaseTable(@PathVariable String tableName);

    @GetExchange(FlowableDefinitions.DATABASE_COLUMN_FOR_SINGLE_TABLE)
    Mono<String> getDatabaseTableColumns(@PathVariable String tableName);

    @GetExchange(FlowableDefinitions.DATABASE_DATA_FOR_SINGLE_ROW)
    Mono<String> getDatabaseTableData(@PathVariable String tableName);
}
