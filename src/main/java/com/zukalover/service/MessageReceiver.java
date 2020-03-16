package com.zukalover.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	
	@Autowired
	ExtractedService extractedService;
	
	private final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	/**
	 * CURRENTLY NOT WORKING: March 2020
	 * Author: Gontse Mochoana
	 * @JmsListener(destination="OutputQueue")
	 * @param message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void listener (String message)
	{
		logger.info( message);
		
		logger.info("SENDING THE CONTENT TO A FILE");
		/**
		 * Thread.sleep(2000);
		 */

		//HERE WE SEND THE MESSAGE TO BE STORED IN TEXT FILE
		extractedService.storeToText(message);
	}
}
