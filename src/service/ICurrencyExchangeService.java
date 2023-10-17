package service;

import model.Currency;

import java.math.BigDecimal;

public interface ICurrencyExchangeService {
    public BigDecimal exchange(Currency sourceCurrency, Currency finalCurrency, BigDecimal amount);

}
