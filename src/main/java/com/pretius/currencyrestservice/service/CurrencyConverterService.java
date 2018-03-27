package com.pretius.currencyrestservice.service;

import com.pretius.currencyrestservice.dto.ConversionResultDto;

import java.math.BigDecimal;

public interface CurrencyConverterService {

    /**
     * @param amount
     * @param sourceCurrency
     * @param destinationCurrency
     * @return
     */
    ConversionResultDto calculateConversion(BigDecimal amount, String sourceCurrency, String destinationCurrency);
}
