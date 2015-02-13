

package com.message.sample.process;

import java.util.Map;

/**
 * Represents a statistical report for received messages.
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class Report {

    /**
     * Total transaction count per currency pair.
     */
    private Map<String, Integer> transactionCount;
    
    /**
     * Total amount sold per currency.
     */
    private Map<String, Double> totalSold;
    
    /**
     * Total amount bought per currency.
     */
    private Map<String, Double> totalBought;

    /**
     * @return the transactionCount
     */
    public Map<String, Integer> getTransactionCount() {
        return transactionCount;
    }

    /**
     * @param transactionCount the transactionCount to set
     */
    public void setTransactionCount(Map<String, Integer> transactionCount) {
        this.transactionCount = transactionCount;
    }

    /**
     * @return the totalSold
     */
    public Map<String, Double> getTotalSold() {
        return totalSold;
    }

    /**
     * @param totalSold the totalSold to set
     */
    public void setTotalSold(Map<String, Double> totalSold) {
        this.totalSold = totalSold;
    }

    /**
     * @return the totalBought
     */
    public Map<String, Double> getTotalBought() {
        return totalBought;
    }

    /**
     * @param totalBought the totalBought to set
     */
    public void setTotalBought(Map<String, Double> totalBought) {
        this.totalBought = totalBought;
    }
    
    
}
