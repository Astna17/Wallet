package com.functionality.td_wallet.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRate {

    private static Map<String, Map<String, Double>> rates = new HashMap<>();

    public static double getExchangeRate(String sourceCurrency, String targetCurrency, LocalDateTime dateUpdated) {
        String formattedDate = dateUpdated.toString();
        return rates.getOrDefault(formattedDate, new HashMap<>()).getOrDefault(sourceCurrency + "-" + targetCurrency, 1.0);
    }
    public static void setExchangeRate(String sourceCurrency, String targetCurrency, double rate, LocalDateTime dateTime) {
        String formattedDate = dateTime.toString();
        rates.computeIfAbsent(formattedDate, k -> new HashMap<>()).put(sourceCurrency + "-" + targetCurrency, rate);
    }
}
