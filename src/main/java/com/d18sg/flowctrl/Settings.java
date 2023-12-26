package com.d18sg.flowctrl;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Component
public class Settings {

    private PropertyChangeSupport support;
    private FlowableDefinitions flowableDefinitions;

    private String baseUrl;
    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) {
        this.support.firePropertyChange("baseUrl", this.baseUrl, baseUrl);
        this.baseUrl = baseUrl;
    }


    private String baseApiUrl;
    public String getBaseApiUrl() { return baseApiUrl; }
    public void setBaseApiUrl(String baseApiUrl) {
        this.support.firePropertyChange("baseApiUrl", this.baseApiUrl, baseApiUrl);
        this.baseApiUrl = baseApiUrl;
    }

    public Settings() {
        this.baseUrl = FlowableDefinitions.DEFAULT_BASE_URL;
        this.baseApiUrl = FlowableDefinitions.DEFAULT_BASE_API_URL;
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "baseUrl='" + baseUrl + '\'' +
                ", baseApiUrl='" + baseApiUrl + '\'' +
                '}';
    }
}