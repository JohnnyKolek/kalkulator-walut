package model;

import java.math.BigDecimal;

public class Currency {
    private String name;
    private String code;
    private int factor;
    private BigDecimal rate;


    public Currency(String name, String code, int factor, BigDecimal rate) {
        this.name = name;
        this.code = code;
        this.factor = factor;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return
                 name + " | " +
                "Kod: " + code + " | " +
                "Przelicznik: " + factor + " | " +
                "Średni kurs: " + rate;
    }
}
