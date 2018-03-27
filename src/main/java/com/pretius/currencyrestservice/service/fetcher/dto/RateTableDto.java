package com.pretius.currencyrestservice.service.fetcher.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@Data
public class RateTableDto implements Serializable {

    private String table;

    private String effectiveDate;

    private List<RateUnitDto> rates;
}
