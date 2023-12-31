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

package com.d18sg.flowctrl.commands;


import com.codernaught.wafle.Credentials;
import com.codernaught.wafle.Settings;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;


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