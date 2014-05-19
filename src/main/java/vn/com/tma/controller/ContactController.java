package vn.com.tma.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.tma.dao.ContactJDBC;
import vn.com.tma.model.Contact;

@Controller
public class ContactController {
	private ApplicationContext context;

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public ModelAndView getContacts() {
		context = new ClassPathXmlApplicationContext("get-contacts-modules.xml");

		ContactJDBC contactJDBC = (ContactJDBC) context.getBean("contactJDBC");
		List<Contact> contactList = contactJDBC.loadAllContacts();

		ModelAndView model = new ModelAndView();
		model.addObject("contactList", contactList);
		model.setViewName("contacts");

		this.checkLogin(model);
		
		return model;
	}
	
	@RequestMapping(value = "/contacts/delete", method = RequestMethod.GET)
	public ModelAndView deleteContacts(@RequestParam(value = "id", required = true) String contactId) {
		context = new ClassPathXmlApplicationContext("get-contacts-modules.xml");

		ContactJDBC contactJDBC = (ContactJDBC) context.getBean("contactJDBC");
		contactJDBC.delete(contactId);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/contacts");

		return model;
	}
	
	@RequestMapping(value = "/contacts/addContact", method = RequestMethod.POST)
	public ModelAndView addNewContact () {
		ModelAndView model = new ModelAndView();
		System.out.println("-------------------------------- Add contact");
		model.setViewName("contacts");
		return model;
	}
	
	private void checkLogin(ModelAndView model) {
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();

			for (GrantedAuthority auths : userDetail.getAuthorities()) {
				model.addObject("role", auths.getAuthority());
			}

			model.addObject("username", userDetail.getUsername());
		}
	}
}
