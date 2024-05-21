package com.felipearruda.currencyconversionexchange.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyInterceptor implements MethodInterceptor {
	
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // Lógica antes da invocação
        System.out.println("Antes do método: " + invocation.getMethod().getName());
        Object result = invocation.proceed();
        // Lógica depois da invocação
        System.out.println("Depois do método: " + invocation.getMethod().getName());
        return result;
    }
    
}
