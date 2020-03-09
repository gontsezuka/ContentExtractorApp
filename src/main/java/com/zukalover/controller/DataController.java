package com.zukalover.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zukalover.entity.DocumentEntity;
import com.zukalover.entity.FileEntity;
import com.zukalover.service.DocumentService;
import com.zukalover.service.FileService;

@RestController
public class DataController {

	@Autowired
	FileService fileService;
	
	@Autowired
	DocumentService documentService;
	
	@GetMapping("/getfiles")
	public List<FileEntity> getFiles()
	{
		return fileService.findAll();
	}
	
	
	@GetMapping("/getdocuments")
	public List<DocumentEntity> getDocuments()
	{
		return documentService.findAllDocuments();
	}
	
	//27 JANUARY 2020 ADDITION
	//RETRIEVES THE CONTENT THAT HAS BEEN EXTRACTED AND SENDS T
	//JAVASCRIPT FOR DISPLAY
	
	@GetMapping("/thepdf")
	public String getFile() throws IOException
	{
		//ModelAndView mav = new ModelAndView();
		String content = "";
		content = new String(Files.readAllBytes(Paths.get("/home/gontse/tesseractOutput/tessOutput.txt")));
		
		return content;
		
	}
}
