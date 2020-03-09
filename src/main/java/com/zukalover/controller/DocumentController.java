package com.zukalover.controller;

import java.io.File;
import java.io.IOException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zukalover.entity.DocumentEntity;
import com.zukalover.entity.FileEntity;
import com.zukalover.entity.SessionEntity;
import com.zukalover.entity.User;
import com.zukalover.service.DocumentService;
import com.zukalover.service.FileService;
import com.zukalover.service.MessageSender;
import com.zukalover.service.SessionEntityService;
import com.zukalover.service.UserService;

@Controller
public class DocumentController {
	String MODE_LOGIN="MODE_LOGIN";
	String MODE_HOME="MODE_HOME";
	
	String MIDDLE_DASHBOARD="DASHBOARD";
	String MIDDLE_DOCUMENTS="DOCUMENTS";
	String MIDDLE_PREVIEW="PREVIEW";
	String MIDDLE_EXTRACTED="EXTRACTED";
	
	String EMPTY_TRUE="TRUE";
	String EMPTY_FALSE="FALSE";
	String MAIN_VIEW="main";
	
	static final String API_CREATEDOCUMENT="/createdocument";
	static final String API_LISTALLDOCUMENTS="/alldocuments/{USERID}";
	static final String API_PREVIEWDOCUMENT="/previewdocument/{USERID}/{documentid}";
	static final String API_DELETEDOCUMENT="/deletedocument/{USERID}/{documentid}";
	static final String API_VIEWEXTRACTEDCONTENT="viewextractedcontent/{USERID}/{documentid}";
	
	static final String USERID="USERID";
	static final String FILEID="FILEID";
	String USERNAME="USERNAME";
	String DOCUMENTS="DOCUMENTS";
	String DOCUMENT="DOCUMENT";
	String DOCUMENTNAME="DOCUMENTNAME";
	String FILES="FILES";
	String MODE="mode";
	String MIDDLE="middle";
	String EMPTY="EMPTY";
	
	
	private Logger logger = Logger.getLogger(DocumentController.class);
	private String extractedFilepath="/srv/www/htdocs/";
	private String extractedFilename="";
	private String globalDocumentName="";
	
	String outputPath="/home/gontse/tesseractOutput/";
	String docFilePath="/home/gontse/UploadedDocs/";
	String imageFilePath="/home/gontse/UploadedImages/";
	String alfrescoOutputPath= "/home/gontse/FROMALFRESCO";
	
	//LOGINREGISTRATION
	@Autowired
	SessionEntityService sessionEntityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	MessageSender messageSender;
	
	@PostMapping(API_CREATEDOCUMENT)
	public ModelAndView createDocument(@RequestParam("document") MultipartFile document,@RequestParam("documentname") String documentname,@RequestParam(FILEID) String FileID,@RequestParam(USERID) Integer UserID) 
	{
		ModelAndView mav = new ModelAndView();
		try {
		String outputPath="/home/gontse/tesseractOutput/";
		String docFilePath="/home/gontse/UploadedDocs/";
		String imageFilePath="/home/gontse/UploadedImages/";
		
		extractedFilename=documentname;
		
		//THE FIRTS SHOULD BE GETTING USERNAME
		
		SessionEntity user = sessionEntityService.findById(UserID); 
		
		DocumentEntity documentEntity = documentService.findByDocumentname(documentname);
		
		FileEntity fileEntity= fileService.findByFilename(FileID);
		
		
		if(documentEntity!=null)
		{
			mav.addObject(MODE, MODE_HOME);
			mav.addObject(MIDDLE,MIDDLE_DASHBOARD);
			mav.addObject(USERID, user.getId());
			mav.addObject(USERNAME, user.getUsername());
			mav.addObject(EMPTY, EMPTY_FALSE);
			mav.addObject("error", "Document already exists");
			mav.addObject(FILES, fileService.findAll());
			mav.setViewName(MAIN_VIEW);
			return mav;
		}
		
		if(fileService.findAll()==null)
		{
			mav.addObject(MODE, MODE_HOME);
			mav.addObject(MIDDLE,MIDDLE_DASHBOARD);
			mav.addObject(USERID, user.getId());
			mav.addObject(USERNAME, user.getUsername());
			mav.addObject(EMPTY, EMPTY_TRUE);
			mav.addObject(FILES, fileService.findAll());
			mav.setViewName(MAIN_VIEW);
			return mav;
		}
		//FileEntity fileEntity = fileService.findById(FILEID);
		
		
		//FROM OLD CODE
		
		//SAVES IT LOCALY
		
		documentService.saveDocument(document, docFilePath);
		
		
		 
		
		//RENAMES DOCUMENT TO THE NAME ENTERED- LOCALLY
	
		File newDoc = documentService.renameDocument(docFilePath, document.getOriginalFilename(), documentname);
		
		
		
		//CREATE THE DOCUMENT OBJECT
		DocumentEntity docObject = new DocumentEntity(newDoc.getName(),fileEntity);
		
		
		//SEND THE DOCUMENT TO ALFRESCO
		String doc_id=documentService.sendToAlfresco(newDoc,fileEntity.getAlfrescoid());
		
		
		//ASSIGN ALRESCO ID TO DOCUMENT
		docObject.setAlfrescoid(doc_id);
		
		
		//SAVE THE DOCUMENT IN THE DATABASE
		documentService.saveDocument(docObject);
		
		
		
		//SEND THE DOCUMENT TO ALFRESCO NO LONGER WOKRING
		//fileService.sendToAlfresco(newDoc,fileEntity.getFilename());
		
		//GET THE FILE NAME WITHOUT .pdf
		String nameNoPDF = documentService.getActualNameNoPDF(docObject.getDocumentname());
		
		//CONVERT TO IMAGE
		int numImages = documentService.convertToImage(newDoc,nameNoPDF,imageFilePath);
		
		
		//SEND TO THE QUEUE
			
						
							
							messageSender.sendMessage(nameNoPDF,imageFilePath,numImages);
							
					
				//Thread.sleep();
		
		//mav.addObject("nav", "TRUE");
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE,MIDDLE_DASHBOARD);
		mav.addObject(USERID, user.getId());
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.addObject(FILES, fileService.findAll());
		mav.setViewName(MAIN_VIEW);
		return mav;
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
		return mav;
	}
	
	//END OF OLD CODE
	
	@GetMapping(API_LISTALLDOCUMENTS)
	public ModelAndView getDocuments(@PathVariable(USERID) Integer UserID)
	{
		ModelAndView mav = new ModelAndView();
		SessionEntity user = sessionEntityService.findById(UserID);
		
		
		//mav.addObject("nav", "TRUE");
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_DOCUMENTS);
		mav.addObject(USERID, user.getId());
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(DOCUMENTS, documentService.findAllDocuments());
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.setViewName(MAIN_VIEW);
		return mav;
		
	}
	
	
	//27 JAN 2020 ADDITION FOR THE PDF FILE FROM ALFRESCO- HAVE TO WORK ON FRONT END
	@GetMapping("/pdf/{USERID}/{fid}/{did}")
	public String getDocumentFromAlfresco(@PathVariable("fid")Integer fid, @PathVariable("did")Integer did,@PathVariable("USERID") Integer USERID) throws IOException
	{
		String fileAlfrescoid= fileService.findFileAlfrescoId(USERID,fid);
		String docAlfrescoid= documentService.findAlfrescoIdByDocumentIdAndfileId(fid, did);
		
		//THIS ACTUALLY FETCHES THE DOCUMENT AT ALFRESCO
		//STORE IT LOCALLY /home/gontse/FROMALFRESCO/targetFile.pdf
		documentService.findAtAlfresco(fileAlfrescoid, docAlfrescoid);
		return "SUCCESS";
	}
	
	
	//02 FEBRUARY 2020
	//TO PREVIEW DOCUMENT FROM ALFESCO/STORE IT IN APACHE AND PREVIEW ON THE USER INTERFACE
	
	@GetMapping(API_PREVIEWDOCUMENT)
	public ModelAndView previewDocument(@PathVariable(USERID) Integer UserID, @PathVariable("documentid") Integer documentID) throws IOException
	{
		ModelAndView mav = new ModelAndView();
		User user = userService.findUserById(UserID);
		DocumentEntity documentEntity = documentService.findDocumentById(documentID);
	
		
		
		
		if(user==null)
		{
			mav.addObject("error", "PLEASE LOGIN");
			mav.addObject(MODE, MODE_LOGIN);
			mav.setViewName(MAIN_VIEW);
		}
		
		//GETS THE DOCUMENT ALFRESCO ID
		String alfrescoid = documentService.findAlfrescoID(documentID);
		
		//GETS THE ACTUAL DOCUMENT FROM ALFRESCO
		String docName = documentService.findDocument(alfrescoid);
		
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_PREVIEW);
		mav.addObject(DOCUMENT, documentEntity);
		mav.addObject(DOCUMENTNAME, docName);
		mav.addObject(USERID, user.getId());
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
	
	
	//TO DELETE A DOCUMENT
	@GetMapping(API_DELETEDOCUMENT)
	public ModelAndView deleteDocument(@PathVariable(USERID)Integer UserID, @PathVariable("documentid") Integer documentID)
	{
		ModelAndView mav = new ModelAndView();
		User user = userService.findUserById(UserID);
		
		if(user==null)
		{
			mav.addObject("error", "PLEASE LOGIN");
			mav.addObject(MODE, MODE_LOGIN);
			mav.setViewName(MAIN_VIEW);
		}
		
		documentService.deleteDocument(documentID);
		
		
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_DOCUMENTS);
		mav.addObject(USERID, user.getId());
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(DOCUMENTS, documentService.findAllDocuments());
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
	
	/*
	//TO VIEW EXTRACTED CONTENT. 1 TEXT DOCUMENT
	@GetMapping("viewextractedcontent/{USERID}")
	public ModelAndView viewContent(@PathVariable("USERID") Integer USERID)
	{
		ModelAndView mav = new ModelAndView();
		
		User user = userService.findUserById(USERID);
		
		if(user==null)
		{
			mav.addObject("error", "PLEASE LOGIN");
			mav.addObject("mode", "MODE_LOGIN");
			mav.setViewName("main");
		}
		
		
		
		mav.addObject("mode", "MODE_HOME");
		mav.addObject("middle", "EXTRACTED");
		mav.addObject("DOCUMENTNAME", "http://localhost/tessOutput.txt");
		mav.addObject("USERID", user.getId());
		mav.addObject("USERNAME", user.getUsername());
		mav.addObject("EMPTY", "FALSE");
		mav.setViewName("main");
		return mav;
	}
	*/
	
	
	@GetMapping(API_VIEWEXTRACTEDCONTENT)
	public ModelAndView viewContent(@PathVariable(USERID) Integer UserID, @PathVariable("documentid") Integer documentID )
	{
		ModelAndView mav = new ModelAndView();
		
		User user = userService.findUserById(UserID);
		DocumentEntity documentEntity = documentService.findDocumentById(documentID);
		String name = documentEntity.getDocumentname();
		name = documentService.getActualNameNoPDF(name);
		
		
		if(user==null)
		{
			mav.addObject("error", "PLEASE LOGIN");
			mav.addObject(MODE, MODE_LOGIN);
			mav.setViewName(MAIN_VIEW);
		}
		
		
		
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_EXTRACTED);
		mav.addObject(DOCUMENTNAME, "http://localhost/"+name +".txt");
		mav.addObject(USERID, user.getId());
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
}