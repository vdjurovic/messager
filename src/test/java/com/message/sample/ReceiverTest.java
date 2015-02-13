

package com.message.sample;

import com.message.sample.process.Report;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class ReceiverTest {
    
    private Receiver receiver;
    private Message msg;
    
    public ReceiverTest() {
    }
    
    @Before
    public void setUp() {
        receiver = new Receiver();
        
        msg = new Message();
        msg.setUserId(123);
        msg.setCurrencyFrom("EUR");
        msg.setCurrencyTo("USD");
        msg.setAmountSell(123.4);
        msg.setAmountBuy(153.3);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of receiveMessage method, of class Receiver.
     */
    @Test
    public void testReceiveMessage() {
        Response response = receiver.receiveMessage(msg);
        assertEquals("Invalid status", Response.Status.OK, response.getStatusInfo());
    }

    /**
     * Test of produceReoport method, of class Receiver.
     */
    @Test
    public void testProduceReoport() throws Exception {
        Response response = receiver.receiveMessage(msg);
        assertEquals("Invalid status", Response.Status.OK, response.getStatusInfo());
        // wait for message to be processed
        Thread.sleep(1000);
        
        Report report = receiver.produceReoport();
        assertEquals("Invalid transaction count", 1, report.getTransactionCount().get("EUR/USD").intValue());
        assertEquals("Invalid amount sold", 123.4, report.getTotalSold().get("EUR").doubleValue(), 0.05);
    }
    
}
