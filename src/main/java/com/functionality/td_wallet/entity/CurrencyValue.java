package org.bibliotheque.Entity;

import java.time.LocalDate;

public class CurrencyValue {
    private int currencyValueId;
    private String sourceCurrencyId;
    private String destinationCurrencyId;
    private double exchangeRate;
    private LocalDate effectiveDate;

    public CurrencyValue(int currencyValueId, String sourceCurrencyId, String destinationCurrencyId,
                         double exchangeRate, LocalDate effectiveDate) {
        this.currencyValueId = currencyValueId;
        this.sourceCurrencyId = sourceCurrencyId;
        this.destinationCurrencyId = destinationCurrencyId;
        this.exchangeRate = exchangeRate;
        this.effectiveDate = effectiveDate;
    }

    public int getCurrencyValueId() {
        return currencyValueId;
    }

    public void setCurrencyValueId(int currencyValueId) {
        this.currencyValueId = currencyValueId;
    }

    public String getSourceCurrencyId() {
        return sourceCurrencyId;
    }

    public void setSourceCurrencyId(String sourceCurrencyId) {
        this.sourceCurrencyId = sourceCurrencyId;
    }

    public String getDestinationCurrencyId() {
        return destinationCurrencyId;
    }

    public void setDestinationCurrencyId(String destinationCurrencyId) {
        this.destinationCurrencyId = destinationCurrencyId;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
