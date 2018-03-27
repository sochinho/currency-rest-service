package com.pretius.currencyrestservice.service;

import com.pretius.currencyrestservice.dto.ConversionResultDto;
import com.pretius.currencyrestservice.exception.CurrencySymbolNotFoundException;
import com.pretius.currencyrestservice.service.fetcher.CurrencyDataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Slf4j
public class CurrencyConverterServiceTest {

    @Mock
    private CurrencyDataFetcher currencyDataFetcher;

    private CurrencyConverterService currencyConverterService;

    private String sourceSymbol = "USD";
    private String destSymbol = "EUR";
    private String wrongSymbol = "URE";

    private BigDecimal sourceRate = BigDecimal.valueOf(3.39);
    private BigDecimal destRate = BigDecimal.valueOf(4.22);

    @Before
    public void setUp() {
        currencyConverterService = new CurrencyConverterServiceImpl(currencyDataFetcher);

        when(currencyDataFetcher.getMidRate(sourceSymbol)).thenReturn(sourceRate);
        when(currencyDataFetcher.getMidRate(destSymbol)).thenReturn(destRate);
        when(currencyDataFetcher.getMidRate(wrongSymbol)).thenReturn(null);
    }

    @Test
    public void testCalculateConversion() {

        BigDecimal amount = BigDecimal.valueOf(10);

        ConversionResultDto conversionResultDto = currencyConverterService
                .calculateConversion(amount, sourceSymbol, destSymbol);

        assertEquals(sourceSymbol, conversionResultDto.getSourceCurrencySymbol());
        assertEquals(destSymbol, conversionResultDto.getDestinationCurrencySymbol());
        assertEquals(amount.toString(), conversionResultDto.getSourceAmount());
        assertEquals(amount.multiply(sourceRate).divide(destRate, BigDecimal.ROUND_CEILING).toString(), conversionResultDto.getDestinationAmount());
    }

    @Test(expected = CurrencySymbolNotFoundException.class)
    public void testCalculateConversionWhenWrongSourceCurrencySymbol() {

        BigDecimal amount = BigDecimal.valueOf(10);

        currencyConverterService
                .calculateConversion(amount, wrongSymbol, destSymbol);
    }

    @Test(expected = CurrencySymbolNotFoundException.class)
    public void testCalculateConversionWhenWrongDestCurrencySymbol() {

        BigDecimal amount = BigDecimal.valueOf(10);

        currencyConverterService
                .calculateConversion(amount, sourceSymbol, wrongSymbol);
    }
}
