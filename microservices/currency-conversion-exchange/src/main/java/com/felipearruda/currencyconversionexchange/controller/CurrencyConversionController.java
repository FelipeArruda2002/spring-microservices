package com.felipearruda.currencyconversionexchange.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.felipearruda.currencyconversionexchange.model.CurrencyConversion;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateConversion(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",  CurrencyConversion.class, uriVariables);
		CurrencyConversion currencyConversion = response.getBody();
		
		return new CurrencyConversion(1000L, from, to, quantity, currencyConversion.getConversionMultiple(), quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
	}
	
}
