package com.d18sg.flowctrl;

import com.d18sg.flowctrl.lib.WorkflowClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlowctrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowctrlApplication.class, args);
	}


	@Bean
	public WorkflowClient flowableClient(WebClientWrapper webClientWrapper, Settings settings) {
		return new WorkflowClient(webClientWrapper, settings);
	}
}

