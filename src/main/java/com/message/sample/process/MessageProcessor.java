

package com.message.sample.process;

import com.message.sample.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Processor for messages consumed by the resource. It receives messages in queue and calls {@link ReportProducer} to 
 * generate report for received messages.
 * This class runs report producer in separate thread to improve efficiency.
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class MessageProcessor {
    
    /**
     * Time to wait to add message to processing queue.
     */
    private final static long DEFAULT_TIMEOUT = 1000;
    
    /**
     * Maximum capacity of the queue.
     */
    private static final int QUEUE_CAPACITY = 1000;

    /**
     * Queue for holding messages.
     */
    private final BlockingQueue<Message> queue;
    
    /**
     * Executor service.
     */
    private final ExecutorService executor;
    
    /**
     * Creates new instance.
     */
    public MessageProcessor(){
        queue = new LinkedBlockingQueue<Message>(QUEUE_CAPACITY);
        // set custom ThreadFactory so that returned threads are daemons
        // this will not block app from shuting down
        executor = Executors.newCachedThreadPool(new ThreadFactory() {
            private final ThreadFactory delegate = Executors.defaultThreadFactory();

            @Override
            public Thread newThread(Runnable r) {
                Thread t = delegate.newThread(r);
                t.setDaemon(true);
                return t;
            }
        });
        init();
    }
    
    /**
     * Initializes queue runner.
     */
    private void init(){
         // start the background daemon consumer thread.
        Thread eventQueueThread = new Thread(new MessageQueueRunner(), "MessageQueue Consumer Thread");
        eventQueueThread.setDaemon(true);
        eventQueueThread.start();
    }
    
    /**
     * Adds message to the queue. This method will wait for up to {@link #DEFAULT_TIMEOUT} miliseconds to put message 
     * to queue if it is full.
     * 
     * @param msg message to add
     * @return <code>true</code> if message is succesfully added, <code>false</code> if not or if timeout expired
     */
    public boolean addMessage(Message msg){
        try {
            return queue.offer(msg, DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch(InterruptedException iex){
            return false;
        }
        
    }
    
    /**
     * Runnable implementation which send messages to processor.
     */
     private class MessageQueueRunner implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    executor.submit(new MessageProcessRunnable(queue.take())); 
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
     
}
