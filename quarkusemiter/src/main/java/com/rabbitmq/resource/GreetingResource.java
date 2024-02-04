package com.rabbitmq.resource;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger; 

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/send")
public class GreetingResource {

	private static final Logger LOG = Logger.getLogger(GreetingResource.class); // Initialize the logger

	@Channel("quarkus-rabbitmq")
	Emitter<JsonObject> emitter;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		try {
			// Create a JSON object with a "hello" property
			JsonObject obj = new JsonObject();
			obj.put("hello", "word");
			
			 // Log an informational message indicating the message is being sent to RabbitMQ
			LOG.info("Sending message to RabbitMQ");
			
			// Send the JSON object to the RabbitMQ channel
			emitter.send(obj);
			
			// Return a success message
			return "Message sent to RabbitMQ";
		} catch (Exception e) {
			// Handle exceptions related to RabbitMQ communication
			LOG.error("Failed to send message to RabbitMQ", e);
			
			// Return an error message
			return "Failed to send message to RabbitMQ";
		}
	}
}