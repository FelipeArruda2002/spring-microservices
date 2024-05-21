package com.felipearruda.currencyconversionexchange.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.felipearruda.currencyconversionexchange.proxy.CurrencyExchangeProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
	public CurrencyExchangeProxy currencyExchangeProxy() {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterfaces(CurrencyExchangeProxy.class);
        proxyFactoryBean.addAdvice(new MyInterceptor());
        return (CurrencyExchangeProxy) proxyFactoryBean.getObject();
	}
	
	@Bean
	public MyInterceptor myInterceptor() {
		return new MyInterceptor();
	}
	

}
