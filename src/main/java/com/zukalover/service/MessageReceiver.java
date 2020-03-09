package com.zukalover.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	@Autowired
	ExtractedService extractedService;
	
	private final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	//@JmsListener(destination="OutputQueue")
	public void listener (String message) throws IOException, InterruptedException
	{
		logger.info("RETURN MESSAGE RECEIVED ON -> "+ message);
		
		logger.info("SENDING THE CONTENT TO A FILE");
		//Thread.sleep(2000);

		//HERE WE SEND THE MESSAGE TO BE STORED IN TEXT FILE
		extractedService.storeToText(message);
	}
}
