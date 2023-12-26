package com.d18sg.flowctrl.commands;


import com.d18sg.flowctrl.lib.WorkflowClient;
import com.d18sg.flowctrl.utility.JsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ModelCommands {
    Logger logger = LoggerFactory.getLogger(ModelCommands.class);
    private WorkflowClient workflowClient;

    private JsonFormatter jsonFormatter;

    public ModelCommands(WorkflowClient workflowClient, JsonFormatter jsonFormatter) {
        this.workflowClient = workflowClient;
        this.jsonFormatter = jsonFormatter;
    }

    @ShellMethod(key = "get-models")
    public String get(@Option(defaultValue = "PRETTY", required = false, arity = CommandRegistration.OptionArity.ZERO_OR_ONE) String printOption) {
        String response = workflowClient.getModels().block().toString();
        return jsonFormatter.format(response, printOption);
    }

    @ShellMethod(key = "get-model")
    public String get(@Option(defaultValue = "PRETTY") String printOption, @Option(description = "Get model") String modelId) {
        String response = workflowClient.getModel(modelId).block().toString();
        return jsonFormatter.format(response, printOption);
    }

}
