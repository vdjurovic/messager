
package com.message.sample.process;

import com.message.sample.Message;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Produces statistical report based on received messages.
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class ReportProducer {
    
    /**
     * Logger for the class.
     */
    private static final Logger LOGGER = Logger.getLogger(ReportProducer.class.getName());
    
    /**
     * Singleton instance of this class.
     */
    private static final ReportProducer INSTANCE;
    
    static {
        INSTANCE = new ReportProducer();
    }
    
    /**
     * Total number of transactions per currency pair.
     */
    private final Map<String, Integer> totalTransactions;
    
    /**
     * Total sold amount per currency.
     */
    private final Map<String, Double> totalSold;
    
    /**
     * Total bought amount per currency.
     */
    private final Map<String, Double> totalBought;
    
    /**
     * Creates new instance.
     */
    private ReportProducer(){
        totalTransactions = new ConcurrentHashMap<String, Integer>();
        totalSold = new ConcurrentHashMap<String, Double>();
        totalBought = new ConcurrentHashMap<String, Double>();
    }
    
    /**
     * Retrieves singleton instance.
     * 
     * @return producer instance
     */
    public static ReportProducer getInstance(){
        return INSTANCE;
    }
    
    /**
     * Adds a message for processing.
     * 
     * @param message message
     */
    public void addMessage(Message message){
        String pair = createCurrencyPair(message);
        calculateTransactionsCount(pair);
        String from = message.getCurrencyFrom();
        double amountSold = message.getAmountSell();
        addToTotal(from, amountSold, totalSold);
        String to = message.getCurrencyTo();
        double amountBought = message.getAmountBuy();
        addToTotal(to, amountBought, totalBought);
        
    }
    
    /**
     * Creates currency pair from message.
     * 
     * @param msg message
     * @return currency pair string (eg. EUR/USD)
     */
    private String createCurrencyPair(Message msg){
        return msg.getCurrencyFrom() + "/" + msg.getCurrencyTo();
    }
    
    /**
     * Calculates transactions count for currecny pair.
     * 
     * @param pair currency pair
     */
    private void calculateTransactionsCount(String pair){
        int count = 0;
        if(totalTransactions.containsKey(pair)){
            count = totalTransactions.get(pair);
        }
        count++;
        totalTransactions.put(pair, count);
        LOGGER.log(Level.INFO, "Total transactions: {0}", totalTransactions);
    }
    
    /**
     * Adds total for bough/sold amount.
     * 
     * @param currency currency
     * @param amount amount
     * @param map bought or sold map
     */
    private void addToTotal(String currency, double amount, Map<String, Double> map){
        double total = 0;
        if(map.containsKey(currency)){
            total = map.get(currency);
        }
        total += amount;
        map.put(currency, total);
        LOGGER.log(Level.INFO, "Total amount: {0}", map);
    }
    
    public Report createReport(){
        Report report = new Report();
        report.setTransactionCount(totalTransactions);
        report.setTotalSold(totalSold);
        report.setTotalBought(totalBought);
        
        return report;
    }
    
    
}
