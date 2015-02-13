

package com.message.sample;

import java.text.ParseException;

/**
 * DTO class which represents message sent to the system.
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */

public class Message {

    /**
     * User ID.
     */
    private long userId;
    
    /**
     * Currency being sold.
     */
    private String currencyFrom;
    
    /**
     * Currency being bought.
     */
    private String currencyTo;
    
    /**
     * Sold amount.
     */
    private double amountSell;
    
    /**
     * Bought amount.
     */
    private double amountBuy;
    
    /**
     * Exchange rate.
     */
    private double rate;
    
    /**
     * Time when the order was placed.
     */
    private String timePlaced;
    
    /**
     * Origin country of the order.
     */
    private String originatingCountry;

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the currencyFrom
     */
    public String getCurrencyFrom() {
        return currencyFrom;
    }

    /**
     * @param currencyFrom the currencyFrom to set
     */
    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    /**
     * @return the currencyTo
     */
    public String getCurrencyTo() {
        return currencyTo;
    }

    /**
     * @param currencyTo the currencyTo to set
     */
    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    /**
     * @return the amountSell
     */
    public double getAmountSell() {
        return amountSell;
    }

    /**
     * @param amountSell the amountSell to set
     */
    public void setAmountSell(double amountSell) {
        this.amountSell = amountSell;
    }

    /**
     * @return the amountBuy
     */
    public double getAmountBuy() {
        return amountBuy;
    }

    /**
     * @param amountBuy the amountBuy to set
     */
    public void setAmountBuy(double amountBuy) {
        this.amountBuy = amountBuy;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return the timePlaced
     */
    public String getTimePlaced() {
        return timePlaced;
    }

    /**
     * @param timePlaced the timePlaced to set
     */
    public void setTimePlaced(String timePlaced) throws ParseException {
        this.timePlaced = timePlaced;
    }

    /**
     * @return the originatingCountry
     */
    public String getOriginatingCountry() {
        return originatingCountry;
    }

    /**
     * @param originatingCountry the originatingCountry to set
     */
    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }
    
    
}
