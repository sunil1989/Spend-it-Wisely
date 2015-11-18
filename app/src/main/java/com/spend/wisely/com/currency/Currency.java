package com.spend.wisely.com.currency;

/**
 * Created by Sunil on 10/30/2015.
 */
public class Currency {
    String currencyName;
    String currecnySign;
    String currencydropdown;

    public String getCurrencydropdown() {
        return currencydropdown;
    }

    public void setCurrencydropdown(String currencydropdown) {
        this.currencydropdown = currencydropdown;
    }

    public Currency(String usd, String s,String dropdown) {
        this.currencyName=usd;
        this.currecnySign=s;
        this.currencydropdown=dropdown;
    }

    public String getCurrencyName() {

        return currencyName.toString();
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrecnySign() {
        return currecnySign;
    }

    public void setCurrecnySign(String currecnySign) {
        this.currecnySign = currecnySign;
    }
}
