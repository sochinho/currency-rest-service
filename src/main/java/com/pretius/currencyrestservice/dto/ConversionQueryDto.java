package com.pretius.currencyrestservice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ConversionQueryDto implements Serializable {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String sourceCurrencySymbol;

    @NotNull
    private String destinationCurrencySymbol;
}
