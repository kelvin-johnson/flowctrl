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




}
