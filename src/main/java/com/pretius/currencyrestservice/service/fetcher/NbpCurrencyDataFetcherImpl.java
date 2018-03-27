package com.pretius.currencyrestservice.service.fetcher;

import com.pretius.currencyrestservice.service.fetcher.dto.RateTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class NbpCurrencyDataFetcherImpl implements CurrencyDataFetcher {

    private final String getDataUrl = "http://api.nbp.pl/api/exchangerates/tables/A/today";

    @Autowired
    private RestTemplate restTemplate;

    private Map<String, BigDecimal> currencyRate = new HashMap<>();

    @PostConstruct
    private void getDataFromNbpServer() {
        RateTableDto rateTableDto = restTemplate.getForObject(getDataUrl, RateTableDto[].class)[0];

        rateTableDto.getRates().forEach(r -> {
            currencyRate.put(r.getCode(), new BigDecimal(r.getMid()));
        });
    }

    @Override
    public BigDecimal getMidRate(String symbol) {
        return currencyRate.get(symbol);
    }
}
