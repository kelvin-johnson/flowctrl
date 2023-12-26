
package com.d18sg.flowctrl.lib.dto;

import java.util.LinkedHashMap;
import java.util.List;
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
        "data",
        "total",
        "start",
        "sort",
        "order",
        "size"
})
@Generated("jsonschema2pojo")
public class ProcessDefinitionsDTO {

    @JsonProperty("data")
    private List<ProcessDefinitionDTO> data;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("start")
    private Integer start;
    @JsonProperty("sort")
    private String sort;
    @JsonProperty("order")
    private String order;
    @JsonProperty("size")
    private Integer size;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("data")
    public List<ProcessDefinitionDTO> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<ProcessDefinitionDTO> data) {
        this.data = data;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("start")
    public Integer getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(Integer start) {
        this.start = start;
    }

    @JsonProperty("sort")
    public String getSort() {
        return sort;
    }

    @JsonProperty("sort")
    public void setSort(String sort) {
        this.sort = sort;
    }

    @JsonProperty("order")
    public String getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(String order) {
        this.order = order;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
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
        ProcessDefinitionsDTO that = (ProcessDefinitionsDTO) o;
        return Objects.equals(data, that.data) && Objects.equals(total, that.total) && Objects.equals(start, that.start) && Objects.equals(sort, that.sort) && Objects.equals(order, that.order) && Objects.equals(size, that.size) && Objects.equals(additionalProperties, that.additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, total, start, sort, order, size, additionalProperties);
    }

    @Override
    public String toString() {
        return "ProcessDefinitions{" +
                "data=" + data +
                ", total=" + total +
                ", start=" + start +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", size=" + size +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}