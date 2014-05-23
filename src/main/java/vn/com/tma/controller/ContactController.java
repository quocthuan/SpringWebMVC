package vn.com.tma.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
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

import com.tma.audit.vo.AuditActionEnum;
import com.tma.audit.vo.PatientDataEnum;
import com.tma.dao.CQLEventRecordDAO;
import com.tma.model.EventRecord;

@Controller
public class ContactController {
	private ApplicationContext context;
	private ContactJDBC contactJDBC;
	private ApplicationContext cassandraContext;

	public ContactController() {
		// TODO Auto-generated constructor stub
		this.context = new ClassPathXmlApplicationContext("get-contacts-modules.xml");
		this.contactJDBC = (ContactJDBC) this.context.getBean("contactJDBC");
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public ModelAndView getContacts() {
		List<Contact> contactList = this.contactJDBC.loadAllContacts();

		ModelAndView model = new ModelAndView();
		model.addObject("contactList", contactList);
		model.setViewName("contacts");

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();

			for (GrantedAuthority auths : userDetail.getAuthorities()) {
				model.addObject("role", auths.getAuthority());
			}

			model.addObject("username", userDetail.getUsername());
		}

		return model;
	}

	@RequestMapping(value = "/contacts/add", method = RequestMethod.GET)
	public ModelAndView showAddContact() {
		ModelAndView model = new ModelAndView();
		model.setViewName("pageContact");

		return model;
	}
	
	@RequestMapping(value = "/contacts/add", method = RequestMethod.POST)
	public ModelAndView addNewContact(@ModelAttribute("contact") Contact contact) {
		this.contactJDBC.insert(contact);

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/contacts");
		return model;
	}
	
	@RequestMapping(value = "/contacts/delete", method = RequestMethod.GET)
	public ModelAndView deleteContacts(@RequestParam(value = "id", required = true) int contactId) {
		this.contactJDBC.delete(contactId);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/contacts");

		return model;
	}
	
	@RequestMapping(value = "/contacts/update", method = RequestMethod.GET)
	public ModelAndView showUpdateContact(@RequestParam(value = "id", required = true) int contactId) {
		Contact editContact = this.contactJDBC.findContactById(contactId);
		ModelAndView model = new ModelAndView();
		
		if (editContact != null) {
			model.addObject("contact", editContact);
		}
		
		model.setViewName("pageContact");

		return model;
	}
	
	@RequestMapping(value = "/contacts/update", method = RequestMethod.POST)
	public ModelAndView updateContacts(@ModelAttribute("contact") Contact contact) {
		this.contactJDBC.update(contact);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/contacts");

		return model;
	}

	@RequestMapping(value = "/contacts/generatedData", method = RequestMethod.GET)
	public ModelAndView generatedDataContacts() {
		ModelAndView model = new ModelAndView();
		
		Map<String, String> values = new HashMap<String, String>();
		values.put("test-key", "test-value");
		int facId = randInt(1, 1000);
		long eventTimeStamp = Calendar.getInstance().getTimeInMillis();
		
		cassandraContext = new ClassPathXmlApplicationContext("classpath*:cassandra_context.xml");
		CQLEventRecordDAO cqlEventRecordDAO = (CQLEventRecordDAO) cassandraContext.getBean("cqlEventRecordDAOImpl");
		EventRecord eventRecord;
		System.out.print("Generating data ");
		for (int i = 0; i < 1000; i++) {
			eventRecord = new EventRecord("abhowt", facId, eventTimeStamp,
					new Integer(1), new Integer(1),
					AuditActionEnum.forValue(randInt(1, 8)), values,
					new PatientDataEnum[] { PatientDataEnum
							.forValue(randPatientEnum()) });
			eventRecord.setClientFname("Marise");
			eventRecord.setClientLname("Ghali");
			eventRecord.setClientMname("");
			eventRecord.setClientNumber("pcc-ghalim");
			eventRecord.setFacName("Audit Report");
			eventRecord.setUserLogin("JaneDoe");
			eventRecord.setUserName("Jane Doe");
			
			try {
				cqlEventRecordDAO.persist(eventRecord);
				System.out.print(".");
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		model.addObject("gene", true);
		model.setViewName("forward:/contacts");

		return model;
	}
	
	@RequestMapping(value = "/contacts/generated", method = RequestMethod.GET)
	public ModelAndView generatedContacts() {
		ModelAndView model = new ModelAndView();
		cassandraContext = new ClassPathXmlApplicationContext("classpath*:cassandra_context.xml");
		CQLEventRecordDAO cqlEventRecordDAO = (CQLEventRecordDAO) cassandraContext.getBean("cqlEventRecordDAOImpl");
		List<EventRecord> generatedContact = cqlEventRecordDAO.query();
		
		model.addObject("generatedContact", generatedContact);
		model.setViewName("generatedContact");

		return model;
	}
	
	@ModelAttribute("contact")
	public Contact createContact() {
	    return new Contact();
	}

	/**
	 * Private helper methods
	 */
	private static int randInt(int min, int max) {

		// Usually this should be a field rather than a method variable so
		// that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static int randPatientEnum() {
		int expectedResult = 0;
		
		do {
			Random rand = new Random();
			expectedResult = rand.nextInt((19 - 1) + 1) + 1;
		} while (expectedResult > 9 && expectedResult < 17);
		
		return expectedResult;
	}
}
