package com.example.SimpleSpringBootApp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleSpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringBootAppApplication.class, args);
		connectAndCreateQueue();
	}

		public static void connectAndCreateQueue() throws UnknownHostException {
		
		String rabbitHost =  "https://openshift-rabbitmq-test-d0400.apps.eu46r.prod.ole.redhat.com";
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