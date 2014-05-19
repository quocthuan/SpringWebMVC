package vn.com.tma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddContactController {
	
	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView addContact () {
		ModelAndView model = new ModelAndView();
		
		
//		if (error != null) {
//			model.addObject("error", "Invalid username and password!");
//		}
//
//		if (logout != null) {
//			model.addObject("msg", "You've been logged out successfully.");
//		}
		model.setViewName("newContact");

		return model;
	}
}
