package com.zukalover.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExtractedService {

private final Logger logger = LoggerFactory.getLogger(ExtractedService.class);
	
	/**
	 * 
	 * This methods stores the extracted content to a text file
	 * @param text
	 */
	public void storeToText(String text) 
	{
		File file = new File("/srv/www/htdocs/tessOutput.txt");//changed from the path in /home/gontse/tessOutput 02 FEB 2020
	
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.info(e.getMessage());
			}
		
	
		BufferedWriter writer= null;
		
		try {
			writer = new BufferedWriter(new FileWriter("/srv/www/htdocs/tessOutput.txt", true));
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		logger.info("WRITING TO FILE");
			
		try {
			writer.newLine();
			writer.write(text);
			writer.close();
		} catch (IOException e) 
		{
			logger.info(e.getMessage());
		}  
		/**
		 * Add new line - true if for appending
		 */
		
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				logger.info(e.getMessage());
			}
		}
		
	}
	
	/**
	 * This method reads content from a text file
	 * Author - Gontse Mochoana
	 * Date - 03 March 2020 
	 * @return
	 */
	public String readText() 
	{
		StringBuilder contentBuilder = new StringBuilder();
		BufferedReader bufferedReader=null;
		try {
			bufferedReader = new BufferedReader(new FileReader("/home/gontse/tesseractOutput/tessOutput.txt"));
		} catch (FileNotFoundException e) 
		{
			logger.info(e.getMessage());
		}
		
		
		String cLine;
		
		try {
			while((cLine = bufferedReader.readLine()) != null)
			{
				contentBuilder.append(cLine).append("\n");
			}
		} catch (IOException e)
		{
			logger.info(e.getMessage());
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				logger.info(e.getMessage());
			}
		}
		return contentBuilder.toString();
	}
}
