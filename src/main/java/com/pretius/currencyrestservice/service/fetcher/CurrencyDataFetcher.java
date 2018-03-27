package com.pretius.currencyrestservice.service.fetcher;

import java.math.BigDecimal;

public interface CurrencyDataFetcher {

    /**
     * @param symbol
     * @return
     */
    BigDecimal getMidRate(String symbol);
}
