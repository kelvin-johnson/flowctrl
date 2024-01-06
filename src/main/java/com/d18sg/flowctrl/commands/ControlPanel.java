package com.d18sg.flowctrl.commands;

import com.d18sg.flowctrl.Credentials;
import com.d18sg.flowctrl.Settings;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Command(group = "control-panel")
public class ControlPanel {

    private Settings settings;
    private Credentials credentials;

    public ControlPanel(Settings settings, Credentials credentials) {
        this.settings = settings;
        this.credentials = credentials;
    }

    @Command(command = "set-base-url")
    public String setBaseUrl(@Option String baseUrl) {
        this.settings.setBaseUrl(baseUrl);
        return "BaseURL set to " + this.settings.getBaseUrl();
    }

    @Command(command = "set-base-api-url")
    public String setBaseApiUrl(@Option String baseApiUrl) {
        this.settings.setBaseApiUrl(baseApiUrl);
        return "BaseAPIURL set to " + this.settings.getBaseApiUrl();
    }

    @Command(command = "authenticate")
    public String authenticate(@Option String username, @Option String password) {
        this.credentials.setUsername(username);
        this.credentials.setPassword(password);

        return "Credentials set to " + this.credentials.toString();
    }
}