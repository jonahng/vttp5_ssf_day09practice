package com.jonah.vttp5_ssf_day09practice.Model;

import java.util.Set;

public class CurrencyConversionForm {

    private String starterCurrency;
    private String convertedCurrency;
    private Long amountToConvert;
    private Long convertedAmount;
    private Set<String> currencyKeys;

    
    public CurrencyConversionForm() {
    }
    

    public CurrencyConversionForm(String starterCurrency, String convertedCurrency, Long amountToConvert,
            Long convertedAmount, Set<String> currencyKeys) {
        this.starterCurrency = starterCurrency;
        this.convertedCurrency = convertedCurrency;
        this.amountToConvert = amountToConvert;
        this.convertedAmount = convertedAmount;
        this.currencyKeys = currencyKeys;
    }


    public String getStarterCurrency() {
        return starterCurrency;
    }


    public void setStarterCurrency(String starterCurrency) {
        this.starterCurrency = starterCurrency;
    }


    public String getConvertedCurrency() {
        return convertedCurrency;
    }


    public void setConvertedCurrency(String convertedCurrency) {
        this.convertedCurrency = convertedCurrency;
    }


    public Long getAmountToConvert() {
        return amountToConvert;
    }


    public void setAmountToConvert(Long amountToConvert) {
        this.amountToConvert = amountToConvert;
    }


    public Long getConvertedAmount() {
        return convertedAmount;
    }


    public void setConvertedAmount(Long convertedAmount) {
        this.convertedAmount = convertedAmount;
    }


    public Set<String> getCurrencyKeys() {
        return currencyKeys;
    }


    public void setCurrencyKeys(Set<String> currencyKeys) {
        this.currencyKeys = currencyKeys;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((starterCurrency == null) ? 0 : starterCurrency.hashCode());
        result = prime * result + ((convertedCurrency == null) ? 0 : convertedCurrency.hashCode());
        result = prime * result + ((amountToConvert == null) ? 0 : amountToConvert.hashCode());
        result = prime * result + ((convertedAmount == null) ? 0 : convertedAmount.hashCode());
        result = prime * result + ((currencyKeys == null) ? 0 : currencyKeys.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CurrencyConversionForm other = (CurrencyConversionForm) obj;
        if (starterCurrency == null) {
            if (other.starterCurrency != null)
                return false;
        } else if (!starterCurrency.equals(other.starterCurrency))
            return false;
        if (convertedCurrency == null) {
            if (other.convertedCurrency != null)
                return false;
        } else if (!convertedCurrency.equals(other.convertedCurrency))
            return false;
        if (amountToConvert == null) {
            if (other.amountToConvert != null)
                return false;
        } else if (!amountToConvert.equals(other.amountToConvert))
            return false;
        if (convertedAmount == null) {
            if (other.convertedAmount != null)
                return false;
        } else if (!convertedAmount.equals(other.convertedAmount))
            return false;
        if (currencyKeys == null) {
            if (other.currencyKeys != null)
                return false;
        } else if (!currencyKeys.equals(other.currencyKeys))
            return false;
        return true;
    }

    
    
    
}