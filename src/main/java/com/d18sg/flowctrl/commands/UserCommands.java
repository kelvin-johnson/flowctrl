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

import com.d18sg.flowctrl.lib.ParameterPackager;
import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jline.terminal.Terminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Command(group = "User Commands")
public class UserCommands {
    Logger logger = LoggerFactory.getLogger(DatabaseTableCommands.class);
    private WorkflowClient workflowClient;

    private Terminal terminal;

    private JsonFormatter jsonFormatter;

    public UserCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter, Terminal terminal) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
        this.terminal = terminal;
    }

    @Command(command = "get-users")
    public String getUsers(
            @Option(description = "compact|raw|pretty", defaultValue = "pretty") String printOption
            , @Option(description = "Only return user with the given id") String id
            , @Option(description = "Only return users with the given firstname") String firstName
            , @Option(description = "Only return users with the given lastname") String lastName
            , @Option(description = "Only return users with the given email") String email
            , @Option(description = "Only return users with a firstname like the given value. Use % as wildcard-character.") String firstNameLike
            , @Option(description = "Only return users with a lastname like the given value. Use % as wildcard-character.") String lastNameLike
            , @Option(description = "Only return users with an email like the given value. Use % as wildcard-character.") String emailLike
            , @Option(description = "Only return users which are a member of the given group.") String memberOfGroup
            , @Option(description = "Only return users which are potential starters for a process-definition with the given id.") String potentialStarter
            , @Option(description = "Field to sort results on, should be one of id, firstName, lastname or email.") String sort
    ) {
        Map<String, String> requestParameters = ParameterPackager.packageParameters(
                  new ImmutablePair<>("id", id)
                , new ImmutablePair<>("firstName", firstName)
                , new ImmutablePair<>("lastName", lastName)
                , new ImmutablePair<>("email", email)
                , new ImmutablePair<>("firstNameLike", firstNameLike)
                , new ImmutablePair<>("lastNameLike", lastNameLike)
                , new ImmutablePair<>("emailLike", emailLike)
                , new ImmutablePair<>("memberOfGroup", memberOfGroup)
                , new ImmutablePair<>("potentialStarter", potentialStarter)
                , new ImmutablePair<>("sort", sort)
        );

        ResponseEntity<String> response = workflowClient.getUsers(requestParameters).block();
        return jsonFormatter.format(response.getBody(), printOption);
    }

    @Command(command = "get-user")
    public String getUser(
            @Option(description = "compact|raw|pretty", defaultValue = "pretty") String printOption
            , @Option(description = "The id of the user to get.") String userId
    ) {
        String response = workflowClient.getUser(userId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @Command(command = "update-user-picture")
    public String updateUserPicture(
            @Option(description = "compact|raw|pretty", defaultValue = "pretty") String printOption
            , @Option(description = "The id of the user to update") String userId
            , @Option(description = "The file name of the picture to use for the user") String fileName
            , @Option(description = "The mime type of the attached file") String mimeType
    ) {
        FileSystemResource fileSystemResource = new FileSystemResource(fileName);
        workflowClient.updateUserPicture(fileSystemResource, userId, mimeType);//.block().toString();
        return "";//""
    }

    @Command(command = "get-user-picture")
    public String getUserPicture(
            @Option(description = "compact|raw|pretty", defaultValue = "pretty") String printOption
            , @Option(description = "The id of the user to get the picture for") String userId
            , @Option(description = "The file name of the picture to use for the user", required = true) String destinationFile
    ) throws IOException {
        Flux<DataBuffer> flux = workflowClient.getUserPicture(userId);

        Path path = Paths.get(destinationFile);
        DataBufferUtils.write(flux, path).block();
        return Files.size(path) + " bytes written to " + destinationFile;
    }

    @Command(command = "do-work")
    public String doWork() {
        return workflowClient.willItWork();
    }

}