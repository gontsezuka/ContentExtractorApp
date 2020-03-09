package com.zukalover.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zukalover.entity.SessionEntity;
import com.zukalover.entity.User;
import com.zukalover.service.FileService;
import com.zukalover.service.SessionEntityService;
import com.zukalover.service.UserService;

@Controller
public class UserController {
	
	String MODE_LOGIN="MODE_LOGIN";
	String MODE_HOME="MODE_HOME";
	String MODE_REGISTER="MODE_REGISTER";
	
	String MIDDLE_DASHBOARD="DASHBOARD";
	String MIDDLE_DOCUMENTS="DOCUMENTS";
	String MIDDLE_PREVIEW="PREVIEW";
	String MIDDLE_EXTRACTED="EXTRACTED";
	String MIDDLE_FILES="FILES";
	String MIDDLE_UPDATE="UPDATE";
	
	String EMPTY_TRUE="TRUE";
	String EMPTY_FALSE="FALSE";
	String MAIN_VIEW="main";
	
	static final String API_REGISTER="/register";
	static final String API_REGISTERUSER="/registeruser";
	static final String API_LOGIN="/login";
	static final String API_LOGINUSER="/loginuser";
	static final String API_UPDATEUSER="/updateuser";
	static final String API_SHOWUPDATEPROFILEPAGE="/updateprofile/{USERID}";
	
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
	private Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	FileService fileService;
	
	@Autowired
	SessionEntityService sessionEntityService;
	
		
	@Autowired
	UserService userService;
	

	
	/**
	 * Author: Gontse Mochoana
	 * Date: 2019 November
	 * Purpose: API to redirect user to registration page
	 * @return
	 */
	@GetMapping(API_REGISTER)
	public ModelAndView showRegistrationPage()
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject(MODE, MODE_REGISTER);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
	
	
	/**
	 * Author: Gonte Mochoana
	 * Date: 2019 November 
	 * Purpose: To register the user into the system
	 * @param user
	 * @param bind
	 * @return
	 */
	
	@PostMapping(API_REGISTERUSER)
	public ModelAndView saveUser(@ModelAttribute User user,BindingResult bind)
	{
		ModelAndView mav = new ModelAndView();
		String message = "";
		
		
		for(User userMan : userService.findAllUsers())
		{
			/**
			if(userMan.getEmail().equals(user.getEmail()))
			{
				message="EMAIL ALREADY EXISTS";
				mav.addObject("error", message);
				mav.addObject("user", user);
				mav.addObject("mode","MODE_REGISTER");
				mav.setViewName("welcomepage");
				return mav;
			}
			*/
			if(userMan.getUsername().equals(user.getUsername()))
			{
				message="USERNAME ALREADY EXISTS";
				mav.addObject("error", message);
				mav.addObject("user", user);
				mav.addObject(MODE, MODE_REGISTER);
				
				mav.setViewName(MAIN_VIEW);
				return mav;
			}
		
		}
		
		userService.saveUser(user);
		mav.addObject("user", user);
		mav.addObject(MODE, MODE_LOGIN);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
	
	
	
	/**
	 * Author: Gontse Mochoana
	 * Date: 2019 November
	 * Purpose: Api to redirect the user to the login page
	 * @return
	 */
	
	@GetMapping(API_LOGIN)
	public ModelAndView showLogin()
	{	
		ModelAndView mav = new ModelAndView();
		mav.addObject(MODE, MODE_LOGIN);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
	/**
	 * Author: Gontse Mochoana
	 * Date: 2019 November
	 * Purpose: API used to login the user into the system
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping(API_LOGINUSER)
	public ModelAndView loginUser(@RequestParam("username") String username, @RequestParam("password") String password)
	{
		String outputPath="/home/gontse/tesseractOutput/";
		String docFilePath="/home/gontse/UploadedDocs/";
		String imageFilePath="/home/gontse/UploadedImages/";
		String alfrescoOutputPath= "/home/gontse/FROMALFRESCO";
		
		ModelAndView mav = new ModelAndView();
		User user = userService.findByUsername(username);
		String message="";
		
		if(user==null)
		{
			message="NO USER AVAILABLE";
			mav.addObject("error",message);
			mav.addObject(MODE, MODE_LOGIN);
			mav.setViewName(MAIN_VIEW);
			return mav;
		}
		/**
			sessionEntity.setId(user.getId());
			sessionEntity.setUsername(user.getUsername());
		*/
		sessionEntityService.createSession(new SessionEntity(user.getId(),user.getUsername()));
		/**
		SessionEntity sessionEntity;
		sessionEntity=sessionEntityService.findById(user.getId());
		*/
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(USERID, user.getId());
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_DASHBOARD);
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.addObject(FILES, fileService.findAll());
		mav.setViewName(MAIN_VIEW);
			
		fileService.clearDirectories(imageFilePath, docFilePath, outputPath,alfrescoOutputPath);
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Author: Gontse Mochoana
	 * Date: 2019 November
	 * Purpose: API used to update the user profile details
	 * @param UserID
	 * @param usernameUpdate
	 * @param passwordUpdate
	 * @return
	 */
	
	
	@PostMapping(API_UPDATEUSER)
	public ModelAndView updateUser(@RequestParam(USERID) Integer UserID,@RequestParam("usernameUpdate") String usernameUpdate,@RequestParam("passwordUpdate") String passwordUpdate )
	{
		ModelAndView mav = new ModelAndView();
		User user = userService.findUserById(UserID);
		
		for(User changed: userService.findAllUsers())
		{
			if(changed.getId()==UserID)
			{
				changed.setPassword(passwordUpdate);
				changed.setUsername(usernameUpdate);
			}
		}
		
		if(user==null)
		{  
			mav.addObject(MODE, MODE_LOGIN);
			mav.addObject("error", "PLEASE LOGIN AGAIN, ERROR ENCOUNTERED");
			sessionEntityService.deleteSession(UserID);
			mav.setViewName(MAIN_VIEW);
		}

		User newUser = userService.findUserById(UserID);
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_DASHBOARD);
		mav.addObject(USERNAME, newUser.getUsername());
		mav.addObject(USERID, USERID);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
	
	
	
	
	
	
	@GetMapping(API_SHOWUPDATEPROFILEPAGE)
	public ModelAndView showUpdateUser(@PathVariable(USERID) Integer UserID )
	{
		ModelAndView mav = new ModelAndView();
		User user = userService.findUserById(UserID);
		
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_UPDATE);
		mav.addObject(USERNAME,user.getUsername());
		mav.addObject(USERID, user.getId());
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.addObject("USER", user);
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
}
