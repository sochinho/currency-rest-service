package com.pretius.currencyrestservice.service.fetcher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmbeddedCurrencyDataFetcherImpl implements CurrencyDataFetcher {

    private Map<String, BigDecimal> currencyRate = new HashMap<>();

    @PostConstruct
    private void initData() {
        currencyRate.put("EUR", BigDecimal.valueOf(4.22));
        currencyRate.put("USD", BigDecimal.valueOf(3.39));
    }

    @Override
    public BigDecimal getMidRate(String symbol) {
        return currencyRate.get(symbol);
    }
}
