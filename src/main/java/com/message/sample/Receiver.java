package com.message.sample;

import com.message.sample.process.MessageProcessor;
import com.message.sample.process.Report;
import com.message.sample.process.ReportProducer;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Basic REST resource for the application. It handles both receiving messaged and 
 * sending report data.
 */
@Path("/")
public class Receiver {
    
    /**
     * Logger used for the class.
     */
    private static final Logger LOGGER = Logger.getLogger(Receiver.class.getName());
    
    /**
     * Message processor.
     */
    private final MessageProcessor processor;
    
    /**
     * Creates new instance of the resource.
     */
    public Receiver(){
        processor = new MessageProcessor();
        LOGGER.info("Created processor");
    }
    
    /**
     * Resource endpoint which consumes messages sent in JSON format.
     * 
     * @param msg received message
     * @return HTTP response (200 if no error, 503 if message processor can not process the message)
     */
    @POST
    @Path("receive")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveMessage(Message msg){
        // send message to processor
        boolean status = processor.addMessage(msg);
        if(status){
            return Response.ok().type(MediaType.TEXT_PLAIN).build();
        }else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
        
    }

    /**
     * Method which sends produced report in JSON format.
     *
     * @return report in JSON format
     */
    @GET
    @Path("report")
    @Produces(MediaType.APPLICATION_JSON)
    public Report produceReoport() {
        return ReportProducer.getInstance().createReport();
    }
}
