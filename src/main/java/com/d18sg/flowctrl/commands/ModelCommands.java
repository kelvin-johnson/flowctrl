package com.d18sg.flowctrl.commands;


import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@Command(group = "Model Commands")
public class ModelCommands {
    Logger logger = LoggerFactory.getLogger(ModelCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public ModelCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }

    @Command(command = "get-models")
    public String get(@Option(defaultValue = "PRETTY") String printOption) {
        String response = workflowClient.getModels().block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @Command(command = "get-model")
    public String get(@Option(defaultValue = "PRETTY") String printOption, @Option(description = "ModelID of model to retrieve") String modelId) {
        String response = workflowClient.getModel(modelId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

}
