package com.pretius.currencyrestservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ConversionResultDto implements Serializable {

    private String sourceCurrencySymbol;
    private String sourceAmount;
    private String destinationCurrencySymbol;
    private String destinationAmount;
}
