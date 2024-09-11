package com.example.SimpleSpringBootApp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SimpleSpringBootAppApplication {

	private static Logger log = LoggerFactory.getLogger(SimpleSpringBootAppApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(SimpleSpringBootAppApplication.class, args);
		connectAndCreateQueue();
	}

    @GetMapping("/test")
    public String testApi() {
        log.info("Request received from the client..");
        log.info("Openshift secret value extracted: ");
        return "Success";
    }

		public static void connectAndCreateQueue() throws UnknownHostException {
		
		String rabbitHost =  "172.30.159.59";
		int rabbitPort = 5672;

		String rabbitUserName = "guest";
		String rabbitPassword = "guest";
		String queueName = "TEST_001";
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(rabbitHost);
		factory.setPort(rabbitPort);
		factory.setUsername(rabbitUserName);
		factory.setPassword(rabbitPassword);


		log.info("Initialising AsyncCommsChannel with parameters host={}; port={}; username={}; queueName={}",
					rabbitHost, rabbitPort, rabbitUserName, queueName);

		try {
			log.info("Inside");
			Connection connection = factory.newConnection();
			Channel initialChannel = connection.createChannel();
			initialChannel.queueDeclare(queueName, true, false, false, null);
			log.info("Done - created QUEUE");
			//this.channel = initialChannel;
			//initialised = true;
		} catch (Exception e) {
			log.error("Exception initialising async channel: ", e);
		}
	}

}
