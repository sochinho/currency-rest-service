package com.pretius.currencyrestservice.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pretius.currencyrestservice.CurrencyRestServiceApplicationTests;
import com.pretius.currencyrestservice.dto.ConversionQueryDto;
import com.pretius.currencyrestservice.service.fetcher.CurrencyDataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyRestServiceApplicationTests.class)
@Slf4j
public class CurrencyConversionControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    @Qualifier("embeddedCurrencyDataFetcherImpl")
    private CurrencyDataFetcher currencyDataFetcher;

    private MockMvc mockMvc;

    private Gson gson = new GsonBuilder().create();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void test_getCurrencyConversionValue_Then_ReturnOk() throws Exception {

        BigDecimal amount = BigDecimal.valueOf(10.00);
        BigDecimal sourceRate = BigDecimal.valueOf(4);
        BigDecimal destRate = BigDecimal.valueOf(3);

        ConversionQueryDto conversionQueryDto = new ConversionQueryDto();
        conversionQueryDto.setAmount(amount);
        conversionQueryDto.setSourceCurrencySymbol("EUR");
        conversionQueryDto.setDestinationCurrencySymbol("USD");

        ResultActions resultActions = mockMvc.perform(
                post("/currency/convert")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(gson.toJson(conversionQueryDto))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.sourceCurrencySymbol", is("EUR")))
                .andExpect(jsonPath("$.destinationCurrencySymbol", is("USD")))
                .andExpect(jsonPath("$.sourceAmount", is(amount.toString())));

//                TODO: fix precision
//                .andExpect(jsonPath("$.destinationAmount", is(amount.multiply(sourceRate).divide(destRate, BigDecimal.ROUND_CEILING).toString())));
    }

    @Test
    public void test_getCurrencyConversionValue_Then_CurrencyNotFound() throws Exception {

        BigDecimal amount = BigDecimal.valueOf(10);
        BigDecimal sourceRate = BigDecimal.valueOf(4.22);
        BigDecimal destRate = BigDecimal.valueOf(3.39);

        ConversionQueryDto conversionQueryDto = new ConversionQueryDto();
        conversionQueryDto.setAmount(amount);
        conversionQueryDto.setSourceCurrencySymbol("EUR");
        conversionQueryDto.setDestinationCurrencySymbol("ERR");

        mockMvc.perform(
                post("/currency/convert")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(gson.toJson(conversionQueryDto))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }


}
