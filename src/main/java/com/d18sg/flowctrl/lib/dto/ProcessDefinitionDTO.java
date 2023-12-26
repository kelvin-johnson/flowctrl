
package com.d18sg.flowctrl.lib.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "url",
        "key",
        "version",
        "name",
        "description",
        "tenantId",
        "deploymentId",
        "deploymentUrl",
        "resource",
        "diagramResource",
        "category",
        "graphicalNotationDefined",
        "suspended",
        "startFormDefined"
})
@Generated("jsonschema2pojo")
public class ProcessDefinitionDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("key")
    private String key;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("tenantId")
    private String tenantId;
    @JsonProperty("deploymentId")
    private String deploymentId;
    @JsonProperty("deploymentUrl")
    private String deploymentUrl;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("diagramResource")
    private String diagramResource;
    @JsonProperty("category")
    private String category;
    @JsonProperty("graphicalNotationDefined")
    private Boolean graphicalNotationDefined;
    @JsonProperty("suspended")
    private Boolean suspended;
    @JsonProperty("startFormDefined")
    private Boolean startFormDefined;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
    }

    @JsonProperty("tenantId")
    public String getTenantId() {
        return tenantId;
    }

    @JsonProperty("tenantId")
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @JsonProperty("deploymentId")
    public String getDeploymentId() {
        return deploymentId;
    }

    @JsonProperty("deploymentId")
    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    @JsonProperty("deploymentUrl")
    public String getDeploymentUrl() {
        return deploymentUrl;
    }

    @JsonProperty("deploymentUrl")
    public void setDeploymentUrl(String deploymentUrl) {
        this.deploymentUrl = deploymentUrl;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    @JsonProperty("resource")
    public void setResource(String resource) {
        this.resource = resource;
    }

    @JsonProperty("diagramResource")
    public String getDiagramResource() {
        return diagramResource;
    }

    @JsonProperty("diagramResource")
    public void setDiagramResource(String diagramResource) {
        this.diagramResource = diagramResource;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("graphicalNotationDefined")
    public Boolean getGraphicalNotationDefined() {
        return graphicalNotationDefined;
    }

    @JsonProperty("graphicalNotationDefined")
    public void setGraphicalNotationDefined(Boolean graphicalNotationDefined) {
        this.graphicalNotationDefined = graphicalNotationDefined;
    }

    @JsonProperty("suspended")
    public Boolean getSuspended() {
        return suspended;
    }

    @JsonProperty("suspended")
    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    @JsonProperty("startFormDefined")
    public Boolean getStartFormDefined() {
        return startFormDefined;
    }

    @JsonProperty("startFormDefined")
    public void setStartFormDefined(Boolean startFormDefined) {
        this.startFormDefined = startFormDefined;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessDefinitionDTO that = (ProcessDefinitionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(url, that.url) && Objects.equals(key, that.key) && Objects.equals(version, that.version) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(tenantId, that.tenantId) && Objects.equals(deploymentId, that.deploymentId) && Objects.equals(deploymentUrl, that.deploymentUrl) && Objects.equals(resource, that.resource) && Objects.equals(diagramResource, that.diagramResource) && Objects.equals(category, that.category) && Objects.equals(graphicalNotationDefined, that.graphicalNotationDefined) && Objects.equals(suspended, that.suspended) && Objects.equals(startFormDefined, that.startFormDefined) && Objects.equals(additionalProperties, that.additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, key, version, name, description, tenantId, deploymentId, deploymentUrl, resource, diagramResource, category, graphicalNotationDefined, suspended, startFormDefined, additionalProperties);
    }

    @Override
    public String toString() {
        return "ProcessDefinition{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", key='" + key + '\'' +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", tenantId='" + tenantId + '\'' +
                ", deploymentId='" + deploymentId + '\'' +
                ", deploymentUrl='" + deploymentUrl + '\'' +
                ", resource='" + resource + '\'' +
                ", diagramResource='" + diagramResource + '\'' +
                ", category='" + category + '\'' +
                ", graphicalNotationDefined=" + graphicalNotationDefined +
                ", suspended=" + suspended +
                ", startFormDefined=" + startFormDefined +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
