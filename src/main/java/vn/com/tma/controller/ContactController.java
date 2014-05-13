package vn.com.tma.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.com.tma.dao.ContactJDBC;
import vn.com.tma.model.Contact;

@Controller
@RequestMapping("/contacts")
public class ContactController {
private ApplicationContext context;

	@RequestMapping(method = RequestMethod.GET)
	public String getContact(ModelMap model) {
		context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		ContactJDBC contactJDBC = (ContactJDBC) context.getBean("contactJDBC");
		
		List<Contact> contactList = contactJDBC.loadAllContacts();
		model.addAttribute("contactList", contactList);

		return "contacts";
	}
}
