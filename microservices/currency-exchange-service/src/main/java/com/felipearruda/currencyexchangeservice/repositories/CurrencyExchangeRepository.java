package com.felipearruda.currencyexchangeservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipearruda.currencyexchangeservice.model.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> { 
	
	CurrencyExchange findByFromAndTo(String from, String to);

}
