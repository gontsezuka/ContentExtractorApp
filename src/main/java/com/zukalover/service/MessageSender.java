package com.zukalover.service;

import java.io.File;
import java.util.Collections;

import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	private Logger logger = LoggerFactory.getLogger(MessageSender.class);
	JmsTemplate jmsTemplate;
	/*
	
	public int getMessageCount()
	{
		return jmsTemplate.browse("OutputQueue", new BrowserCallback<Integer>(){
			@Override
			public Integer doInJms(Session session, QueueBrowser browser) throws JMSException {
				return Collections.list(browser.getEnumeration()).size();
			}
		});
	}
	*/
	
	@Autowired
	public MessageSender(JmsTemplate jmsTemplate)
	{
		this.jmsTemplate=jmsTemplate;
	}
	
	public void sendMessage(String imageName,String filePath,int counter)
	{	
		try {
		String imageNam = imageName;
		String filePat = filePath;
		int count = counter;
		
		
		// MAIN APP SENDER
		logger.info("===================================================================");
		logger.info("SENDING TO QUEUE");
		for(int x=0 ; x<count ; x++)
		{
			int s = x +1;
			File theImage = new File(filePat+imageNam+"-"+s+".jpg");
			
			String message = theImage.getAbsolutePath();
			/**
			jmsTemplate.convertAndSend("destination", "message");
			*/
			this.jmsTemplate.convertAndSend("imageQueue1", message);
			//Thread.sleep(10000);
			logger.info("image : "+s+"has been sent");
			
		}
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
}
