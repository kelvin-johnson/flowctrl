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

package com.d18sg.flowctrl;

import com.codernaught.wafle.Credentials;
import com.codernaught.wafle.Settings;
import com.codernaught.wafle.WebClientWrapper;
import com.codernaught.wafle.WorkflowClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = {"com.codernaught.wafle", "com.d18sg.flowctrl"})
@CommandScan
public class FlowctrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowctrlApplication.class, args);
	}

	//@Bean
	public Credentials credentials() {
		return new Credentials();
	}

	//@Bean
	public Settings settings() {
		return new Settings();
	}

	//@Bean
	public WebClientWrapper webClientWrapper(WebClient.Builder webClientBuilder, Credentials credentials, Settings settings) {
		return new WebClientWrapper(webClientBuilder, credentials, settings);
	}
	@Bean
	public WorkflowClient flowableClient(WebClientWrapper webClientWrapper, Settings settings) {
		return new WorkflowClient(webClientWrapper, settings);
	}

}

