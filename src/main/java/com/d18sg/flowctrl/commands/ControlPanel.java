package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.Credentials;
import com.d18sg.flowctrl.Settings;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ControlPanel {

    private Settings settings;
    private Credentials credentials;

    public ControlPanel(Settings settings, Credentials credentials) {
        this.settings = settings;
        this.credentials = credentials;
    }

    @ShellMethod(key = "set-base-url")
    public String setBaseUrl(@ShellOption String baseUrl) {
        this.settings.setBaseUrl(baseUrl);
        return "BaseURL set to " + this.settings.getBaseUrl();
    }

    @ShellMethod(key = "set-base-api-url")
    public String setBaseApiUrl(@ShellOption String baseApiUrl) {
        this.settings.setBaseApiUrl(baseApiUrl);
        return "BaseAPIURL set to " + this.settings.getBaseApiUrl();
    }

    @ShellMethod(key = "authenticate")
    public String authenticate(@ShellOption String username, @ShellOption String password) {
        this.credentials.setUsername(username);
        this.credentials.setPassword(password);

        return "Credentials set to " + this.credentials.toString();
    }
}