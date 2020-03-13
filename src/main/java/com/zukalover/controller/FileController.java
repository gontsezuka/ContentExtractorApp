package com.zukalover.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zukalover.entity.FileEntity;
import com.zukalover.entity.User;
import com.zukalover.service.FileService;
import com.zukalover.service.UserService;

@Controller
public class FileController {

	
	static final String MODE_LOGIN="MODE_LOGIN";
	static final String MODE_HOME="MODE_HOME";
	
	static final String MIDDLE_DASHBOARD="DASHBOARD";
	static final String MIDDLE_DOCUMENTS="DOCUMENTS";
	static final String MIDDLE_PREVIEW="PREVIEW";
	static final String MIDDLE_EXTRACTED="EXTRACTED";
	static final String MIDDLE_FILES="FILES";
	
	static final String EMPTY_TRUE="TRUE";
	static final String EMPTY_FALSE="FALSE";
	static final String MAIN_VIEW="main";
	
	static final String API_CREATEDOCUMENT="/createdocument";
	static final String API_LISTALLDOCUMENTS="/alldocuments/{USERID}";
	static final String API_PREVIEWDOCUMENT="/previewdocument/{USERID}/{documentid}";
	static final String API_DELETEDOCUMENT="/deletedocument/{USERID}/{documentid}";
	static final String API_VIEWEXTRACTEDCONTENT="viewextractedcontent/{USERID}/{documentid}";
	
	static final String USERID="USERID";
	static final String FILEID="FILEID";
	
	
	static final String USERNAME="USERNAME";
	static final String DOCUMENTS="DOCUMENTS";
	static final String DOCUMENT="DOCUMENT";
	static final String DOCUMENTNAME="DOCUMENTNAME";
	static final String FILES="FILES";
	static final String MODE="mode";
	static final String MIDDLE="middle";
	static final String EMPTY="EMPTY";
	static final String ERROR="error";

	
	private Logger logger = Logger.getLogger(FileController.class);

	@Autowired
	FileService fileService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createfile")
	public ModelAndView getCreateFile(@ModelAttribute FileEntity file,@RequestParam(USERID) Integer userid, RedirectAttributes rd)
	{	
		ModelAndView mav = new ModelAndView();
		String message = "";
		
		User user = userService.findUserById(userid);
		
		FileEntity theFile = fileService.findByFilename(file.getFilename());
		
		if(theFile!=null)
		{
			message="THE FILE ALREADY EXISTS";
			mav.addObject(MODE, MODE_HOME);
			mav.addObject(MIDDLE, MIDDLE_DASHBOARD);
			mav.addObject(USERID, user.getId());
			mav.addObject(USERNAME, user.getUsername());
			mav.addObject(ERROR, message);
			mav.addObject(FILES, fileService.findAll());
			mav.setViewName(MAIN_VIEW);
			return mav;
		}
		
		String fileAlfrescoID=fileService.sendToAlfresco(file.getFilename());
		file.setAlfrescoid(fileAlfrescoID);
		file.setUser(user);
		fileService.saveFile(file);
		
		
		if(user==null)
		{
			mav.addObject(MODE, MODE_LOGIN);
			mav.setViewName(MAIN_VIEW);
			return mav;
		}
		/**
		fileService.saveFile(file);
		
		CHANGE FROM MODEL AND VIEW TO REDIRECT
		OLD SEDN TO ALFRESCO
		fileService.sendToAlfresco(file.getFilename());
		file.setUser(user);
		fileService.saveFile(file);
		*/
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_DASHBOARD);
		mav.addObject(USERID, user.getId());
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.addObject(FILES, fileService.findAll());
		mav.setViewName(MAIN_VIEW); 
		
		return mav;
		
	
	}
	
	@GetMapping("/home/{USERID}")
	public ModelAndView getHome(@PathVariable(USERID) Integer userid)
	{

		ModelAndView mav = new ModelAndView();
		
		User user = userService.findUserById(userid);
		
		
		mav.addObject(USERID, user.getId());
		mav.addObject(USERNAME, user.getUsername());
		
		
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_DASHBOARD);
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.addObject(FILES, fileService.findAll());
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
	
	
	@GetMapping("/allfiles/{USERID}")
	public ModelAndView showAllFiles(@PathVariable(USERID) Integer userid)
	{
		User user = userService.findUserById(userid);
		ModelAndView mav = new ModelAndView();
		
		
		
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(USERID, user.getId());
		mav.addObject(FILES, fileService.findAll());
		mav.addObject(MIDDLE, MIDDLE_FILES);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
	
	@GetMapping("/deletefile/{USERID}/{fileid}")
	public ModelAndView deleteFile(@PathVariable(USERID) Integer userid,@PathVariable("fileid") Integer fileid)
	{
		ModelAndView mav = new ModelAndView();

		User user = userService.findUserById(userid);
		FileEntity fileEntity = fileService.findById(fileid);
		
		
		if(user==null)
		{
			mav.addObject(ERROR, "PLEASE LOGIN");
			mav.addObject(MODE, MODE_LOGIN);
			mav.setViewName(MAIN_VIEW);
		}
		
		
		
		try{
		fileService.deleteById(fileid);
		fileService.deleteFromAlfresco(fileEntity);
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(USERID, user.getId());
		mav.addObject(FILES, fileService.findAll());
		mav.addObject(MIDDLE, MIDDLE_FILES);
		mav.setViewName(MAIN_VIEW);
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
		return mav;
	}
}
