package com.pretius.currencyrestservice.web.handler;

import com.pretius.currencyrestservice.exception.InternalDataFetcherException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        log.error("Rest client error. Status : {}, Message : {}",
                clientHttpResponse.getStatusCode(), clientHttpResponse.getBody());
        throw new InternalDataFetcherException();
    }
}
