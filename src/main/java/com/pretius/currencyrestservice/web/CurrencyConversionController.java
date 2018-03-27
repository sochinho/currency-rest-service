package com.pretius.currencyrestservice.web;

import com.pretius.currencyrestservice.dto.ConversionQueryDto;
import com.pretius.currencyrestservice.dto.ConversionResultDto;
import com.pretius.currencyrestservice.service.CurrencyConverterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/currency")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyConversionController {

    private CurrencyConverterService currencyConverterService;

    @PostMapping("/convert")
    public ResponseEntity<ConversionResultDto> getCurrencyConversionValue(@Valid @RequestBody ConversionQueryDto currencyQueryDto) {
        return ResponseEntity
                .ok(currencyConverterService.calculateConversion(currencyQueryDto.getAmount(), currencyQueryDto.getSourceCurrencySymbol(), currencyQueryDto.getDestinationCurrencySymbol()));
    }
}
