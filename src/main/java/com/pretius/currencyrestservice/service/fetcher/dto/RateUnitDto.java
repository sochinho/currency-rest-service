package com.pretius.currencyrestservice.service.fetcher.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@Data
public class RateUnitDto implements Serializable {

    private String currency;

    private String code;

    private String mid;
}
