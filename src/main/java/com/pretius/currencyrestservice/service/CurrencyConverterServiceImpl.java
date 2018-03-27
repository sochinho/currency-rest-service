package com.pretius.currencyrestservice.service;

import com.pretius.currencyrestservice.dto.ConversionResultDto;
import com.pretius.currencyrestservice.exception.CurrencySymbolNotFoundException;
import com.pretius.currencyrestservice.service.fetcher.CurrencyDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private final CurrencyDataFetcher currencyDataFetcher;

    private final String baseConversionSymbol = "PLN";

    @Override
    public ConversionResultDto calculateConversion(BigDecimal amount, String sourceCurrency, String destinationCurrency) {

        if (sourceCurrency.equals(baseConversionSymbol)) {
            BigDecimal destCurrencyMidRate = Optional.ofNullable(currencyDataFetcher.getMidRate(destinationCurrency))
                    .orElseThrow(CurrencySymbolNotFoundException::new);

            return ConversionResultDto.builder()
                    .sourceAmount(amount.toString())
                    .sourceCurrencySymbol(sourceCurrency)
                    .destinationCurrencySymbol(destinationCurrency)
                    .destinationAmount(amount.divide(destCurrencyMidRate, BigDecimal.ROUND_CEILING).toString())
                    .build();
        }

        if (destinationCurrency.equals(baseConversionSymbol)) {
            BigDecimal sourceCurrencyMidRate = Optional.ofNullable(currencyDataFetcher.getMidRate(sourceCurrency))
                    .orElseThrow(CurrencySymbolNotFoundException::new);

            return ConversionResultDto.builder()
                    .sourceAmount(amount.toString())
                    .sourceCurrencySymbol(sourceCurrency)
                    .destinationCurrencySymbol(destinationCurrency)
                    .destinationAmount(amount.multiply(sourceCurrencyMidRate).toString())
                    .build();
        }

        BigDecimal sourceCurrencyMidRate = Optional.ofNullable(currencyDataFetcher.getMidRate(sourceCurrency))
                .orElseThrow(CurrencySymbolNotFoundException::new);

        BigDecimal destCurrencyMidRate = Optional.ofNullable(currencyDataFetcher.getMidRate(destinationCurrency))
                .orElseThrow(CurrencySymbolNotFoundException::new);

        return ConversionResultDto.builder()
                .sourceAmount(amount.toString())
                .sourceCurrencySymbol(sourceCurrency)
                .destinationCurrencySymbol(destinationCurrency)
                .destinationAmount(amount.multiply(sourceCurrencyMidRate).divide(destCurrencyMidRate, BigDecimal.ROUND_CEILING).toString())
                .build();
    }
}
