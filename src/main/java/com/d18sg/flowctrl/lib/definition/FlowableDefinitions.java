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

package com.d18sg.flowctrl.lib.definition;

public class FlowableDefinitions {

    public static final String DEFAULT_BASE_URL                 = "http://localhost:8080";
    public static final String DEFAULT_FLOWABLE_BASE_API_URL    = "/flowable-rest";
    public static final String DEFAULT_ACTIVITI_BASE_API_URL    = "/activiti-rest";
    public static final String DEFAULT_CAMUNDA_BASE_API_URL     = "/";


    public static final String DEPLOYMENTS                = "/service/repository/deployments";
    public static final String DEPLOYMENT                 = "/service/repository/deployments/{deploymentId}";
    public static final String DEPLOYMENT_RESOURCES       = "/service/repository/deployments/{deploymentId}/resources";
    public static final String DEPLOYMENT_RESOURCE        = "/service/repository/deployments/{deploymentId}/resources/{resourceId}";
    public static final String DEPLOYMENT_RESOURCE_DATA   = "/service/repository/deployments/{deploymentId}/resourcedata/{resourceId}";


    public static final String PROCESS_DEFINITIONS                      = "/service/repository/process-definitions";
    public static final String PROCESS_DEFINITION                       = "/service/repository/process-definitions/{processDefinitionId}";
    public static final String PROCESS_DEFINITION_RESOURCES_DATA        = "/service/repository/process-definitions/{processDefinitionId}/resourcedata";
    public static final String PROCESS_DEFINITION_MODEL                 = "/service/repository/process-definitions/{processDefinitionId}/model";
    public static final String PROCESS_DEFINITION_CANDIDATE_STARTERS    = "/service/repository/process-definitions/{processDefinitionId}/identitylinks";
    public static final String PROCESS_DEFINITION_CANDIDATE_STARTER     = "/service/repository/process-definitions/{processDefinitionId}/identitylinks/{family}/{identityId}";


    public static final String MODELS                      = "/service/repository/models";
    public static final String MODEL                       = "/service/repository/models/{modelId}";
    public static final String MODEL_SOURCE                = "/service/repository/models/{modelId}/source";
    public static final String MODEL_SOURCE_EXTRA          = "/service/repository/models/{modelId}/source-extra";


    public static final String DATABASE_TABLES                    = "/service/management/tables";
    public static final String DATABASE_TABLE                     = "/service/management/tables/{tableName}";
    public static final String DATABASE_COLUMN_FOR_SINGLE_TABLE   = "/service/management/tables/{tableName}/columns";
    public static final String DATABASE_DATA_FOR_SINGLE_ROW       = "/service/management/tables/{tableName}/data";


    public static final String JOBS   = "/service/management/jobs";
    public static final String JOB    = "/service/management/jobs/{jobId}";


    public static final String USERS            = "/service/identity/users";
    public static final String USER             = "/service/identity/users/{userId}";
    public static final String USER_PICTURE     = "/service/identity/users/{userId}/picture";
    public static final String USER_INFO_LIST   = "/service/identity/users/{userId}/info";
    public static final String USER_INFO        = "/service/identity/users/{userId}/info/{key}";



    public static final String GROUPS               = "/service/identity/groups";
    public static final String GROUP                = "/service/identity/groups/{groupId}";
    public static final String GROUP_MEMBERS_ADD    = "/service/identity/groups/{groupId}/members";
    public static final String GROUP_MEMBER_DELETE  = "/service/identity/groups/{groupId}/members/{userId}";




}
