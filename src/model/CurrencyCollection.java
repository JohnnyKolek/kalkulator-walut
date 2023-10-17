package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyCollection {

    private final List<Currency> currencyList;

    public CurrencyCollection()
    {
        currencyList = new ArrayList<>();
    }
    public List<Currency> getCurrencyList() {
        return this.currencyList;
    }

    public Optional<Currency> getCurrencyByCode(String code) {
        for (Currency currency : currencyList)
        {
            if (currency.getCode().equals(code))
            {
                return Optional.of(currency);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString()
    {
        StringBuilder temp = new StringBuilder();
        for (Currency x : currencyList)
        {
            temp.append("Code = ").append(x.getCode()).append(" | Factor = ").append(x.getFactor()).append(" | Rate = ").append(x.getRate()).append("\n");
        }
        return temp.toString();
    }
}
