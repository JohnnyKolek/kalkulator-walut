package service;

import model.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyExchangeService implements ICurrencyExchangeService{

    private static CurrencyExchangeService instance = null;

    private CurrencyExchangeService() {
    }

    public static CurrencyExchangeService getInstance(){
        if (instance == null){
            instance = new CurrencyExchangeService();
        }
        return instance;
    }
    @Override
    public BigDecimal exchange(Currency sourceCurrency, Currency finalCurrency, BigDecimal amount) {
        return amount.multiply(sourceCurrency.getRate()).multiply(BigDecimal.valueOf(finalCurrency.getFactor()))
                .divide((finalCurrency.getRate().multiply(BigDecimal.valueOf(sourceCurrency.getFactor()))),2,RoundingMode.HALF_UP);
    }
}
