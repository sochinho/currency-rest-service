package com.pretius.currencyrestservice.service;

import com.pretius.currencyrestservice.dto.ConversionResultDto;

import java.math.BigDecimal;

public interface CurrencyConverterService {

    ConversionResultDto calculateConversion(BigDecimal amount, String sourceCurrency, String destinationCurrency);
}
