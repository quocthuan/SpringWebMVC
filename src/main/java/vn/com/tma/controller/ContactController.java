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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.tma.dao.ContactJDBC;
import vn.com.tma.model.Contact;

@Controller
public class ContactController {
	private ApplicationContext context;

	public ContactController() {
		// TODO Auto-generated constructor stub
		this.context = new ClassPathXmlApplicationContext("get-contacts-modules.xml");
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public ModelAndView getContacts() {
		ContactJDBC contactJDBC = (ContactJDBC) this.context
				.getBean("contactJDBC");
		List<Contact> contactList = contactJDBC.loadAllContacts();

		ModelAndView model = new ModelAndView();
		model.addObject("contactList", contactList);
		model.setViewName("contacts");

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

		return model;
	}

	@RequestMapping(value = "/contacts/newContact", method = RequestMethod.GET)
	public ModelAndView showAddContact() {
		ModelAndView model = new ModelAndView();
		model.setViewName("newContact");

		return model;
	}
	
	@RequestMapping(value = "/contacts/newContact", method = RequestMethod.POST)
	public ModelAndView addNewContact(@ModelAttribute("contact") Contact contact) {
		ContactJDBC contactJDBC = (ContactJDBC) this.context.getBean("contactJDBC");
		contactJDBC.insert(contact);

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/contacts");
		return model;
	}
	
	@RequestMapping(value = "/contacts/delete", method = RequestMethod.GET)
	public ModelAndView deleteContacts(
			@RequestParam(value = "id", required = true) String contactId) {
		ContactJDBC contactJDBC = (ContactJDBC) this.context
				.getBean("contactJDBC");
		contactJDBC.delete(contactId);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/contacts");

		return model;
	}

	@ModelAttribute("contact")
	public Contact createContact() {
	    return new Contact();
	}

}
