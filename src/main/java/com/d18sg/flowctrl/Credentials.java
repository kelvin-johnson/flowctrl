package com.d18sg.flowctrl;

import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Component
public class Credentials {

    private PropertyChangeSupport support;

    private String username = "rest-admin";
    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.support.firePropertyChange("username", this.username, username);
        this.username = username;
    }

    private String password = "test";
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.support.firePropertyChange("password", this.password, password);
        this.password = password;
    }

    public Credentials() {
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
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
