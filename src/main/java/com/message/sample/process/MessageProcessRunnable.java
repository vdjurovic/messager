

package com.message.sample.process;

import com.message.sample.Message;

/**
 * An implementation of {@link Runnable} which sends message to report producer.
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class MessageProcessRunnable implements Runnable{
    
    /**
     * Message to process.
     */
    private final Message message;
    
    /**
     * Creates new instance.
     * 
     * @param msg message
     */
    public MessageProcessRunnable(Message msg){
        this.message = msg;
    }

    @Override
    public void run() {
        ReportProducer.getInstance().addMessage(message);
    }

}
