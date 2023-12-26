package com.d18sg.flowctrl;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;



@Component
public class WebClientWrapper implements PropertyChangeListener {

    Logger logger = org.slf4j.LoggerFactory.getLogger(WebClientWrapper.class);
    private WebClient webClient;

    public WebClient getWebClient() { return this.webClient; }

    private Credentials credentials;
    private Settings settings;
    private WebClient.Builder webClientBuilder;


    public WebClientWrapper(WebClient.Builder webClientBuilder, Credentials credentials, Settings settings) {
        credentials.addPropertyChangeListener(this);
        settings.addPropertyChangeListener(this);
        this.credentials = credentials;
        this.webClientBuilder = webClientBuilder;
        this.settings = settings;
        this.webClient = webClient();

exe    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        logger.info("propertyChange: " + evt.getPropertyName() + " " + evt.getOldValue() + " " + evt.getNewValue());
        if (evt.getPropertyName().equals("username")) {
            logger.info("Web client updated! New username ");
            this.webClient = webClient(settings.getBaseUrl(),(String)evt.getNewValue(),credentials.getPassword());
        }

        if(evt.getPropertyName().equals("password") ) {
            logger.info("Web client updated! New password");
            this.webClient = webClient(settings.getBaseUrl(),credentials.getUsername(),(String)evt.getNewValue());
        }

        if(evt.getPropertyName().equals("baseUrl")) {
            logger.info("Web client updated! New baseUrl");
            this.webClient = webClient((String)evt.getNewValue(),credentials.getUsername(),credentials.getPassword());
        }
    }

    private WebClient webClient(String baseUrl, String username, String password) {
        logger.info("private:webClient: " + credentials.toString() + " " + settings.toString());
        WebClient webClient = webClientBuilder
                .baseUrl(baseUrl.isBlank() ? FlowableDefinitions.DEFAULT_BASE_URL : baseUrl)
                .filter(ExchangeFilterFunctions.basicAuthentication(username, password))
                .build();
        return webClient;
    }

    private WebClient webClient() {
        logger.info("private:webClient: " + credentials.toString() + " " + settings.toString());
        String baseUrl = settings.getBaseUrl().isBlank() ? FlowableDefinitions.DEFAULT_BASE_URL : settings.getBaseUrl();
        String baseApiUrl = settings.getBaseApiUrl().isBlank() ? FlowableDefinitions.DEFAULT_BASE_API_URL : settings.getBaseApiUrl();

        WebClient webClient = webClientBuilder
                .baseUrl(baseUrl + baseApiUrl)
                .filter(ExchangeFilterFunctions.basicAuthentication(credentials.getUsername(), credentials.getPassword()))
                .build();
        return webClient;
    }
}