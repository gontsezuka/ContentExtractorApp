package com.zukalover.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.zukalover.entity.User;
import com.zukalover.service.FileService;
import com.zukalover.service.SessionEntityService;
import com.zukalover.service.UserService;

@Controller
public class ApplicationController {

	String MODE_LOGIN="MODE_LOGIN";
	String MODE_HOME="MODE_HOME";
	String MIDDLE_DASHBOARD="DASHBOARD";
	String EMPTY_TRUE="TRUE";
	String EMPTY_FALSE="FALSE";
	String MAIN_VIEW="main";
	
	static final String API_LOGOUT="/logout/{USERID}";
	static final String API_DASHBOARD="/dashboard/{USERID}";
	
	static final String USERID="USERID";
	String USERNAME="USERNAME";
	String FILES="FILES";
	String MODE="mode";
	String MIDDLE="middle";
	String EMPTY="EMPTY";
	
	
	@Autowired
	FileService fileService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SessionEntityService sessionEntityService;
	
	/**NOT WORKING
	@GetMapping("/")
	public String home(HttpServletRequest request) 
	{
		request.setAttribute("mode", "MODE_HOME");
		
		request.setAttribute("nav", "TRUE");
		request.setAttribute("EMPTY", "FALSE");
		request.setAttribute("FILES", fileService.findAll());
		return "welcomepage";
	}
	*/
	
	//TO LOGOUT
	@GetMapping(API_LOGOUT)
	public ModelAndView logout(@PathVariable(USERID) Integer UserID)
	{
		ModelAndView mav = new ModelAndView();
		
		sessionEntityService.deleteSession(UserID);
		
		mav.addObject(MODE, MODE_LOGIN);
		mav.setViewName(MAIN_VIEW);
		return mav;		
	}
	
	
	//GO TO DASHBOARD
	@GetMapping(API_DASHBOARD)
	public ModelAndView getDashboard(@PathVariable(USERID) Integer userid)
	{
		ModelAndView mav = new ModelAndView();
		User user = userService.findUserById(userid);
		
		if(user == null )
		{
			mav.setViewName(MAIN_VIEW);
			mav.addObject(MODE, MODE_LOGIN);
			return mav;
		}
		
		
		mav.addObject(USERNAME, user.getUsername());
		mav.addObject(USERID, user.getId());
		mav.addObject(MODE, MODE_HOME);
		mav.addObject(MIDDLE, MIDDLE_DASHBOARD);
		mav.addObject(EMPTY, EMPTY_FALSE);
		mav.addObject(FILES,fileService.findAll());
		mav.setViewName(MAIN_VIEW);
		return mav;
	}
}
