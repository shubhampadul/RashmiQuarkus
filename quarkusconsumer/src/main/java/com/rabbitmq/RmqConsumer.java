package com.rabbitmq;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RmqConsumer {
	
	@Incoming("quarkus-rabbitmq")
	public void process(JsonObject msg) {
		
		try {
		// Process the incoming message
		 Log.info("Received message--------------------------------------: " + msg.toString());
		 
		// Your additional processing logic here
		 
		}
		catch (Exception e) {
			// Handle the exception
            Log.error("Error processing message: " + msg.toString(), e);
		}
	}

}
