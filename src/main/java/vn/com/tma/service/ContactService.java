package vn.com.tma.service;

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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tma.audit.vo.AuditActionEnum;
import com.tma.audit.vo.PatientDataEnum;
import com.tma.dao.CQLEventRecordDAO;
import com.tma.model.EventRecord;

@Controller
public class ContactService {
	private ApplicationContext cassandraContext;

	@RequestMapping(value = "/service/contact", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<EventRecord> getRecords() {
		cassandraContext = new ClassPathXmlApplicationContext(
				"classpath*:cassandra_context.xml");
		CQLEventRecordDAO cqlEventRecordDAO = (CQLEventRecordDAO) cassandraContext
				.getBean("cqlEventRecordDAOImpl");
		List<EventRecord> generatedContact = cqlEventRecordDAO.query();

		return generatedContact;
	}

	@RequestMapping(value = "/service/generatedContact", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public boolean generatedContacts() {
		cassandraContext = new ClassPathXmlApplicationContext(
				"classpath*:cassandra_context.xml");

		Map<String, String> values = new HashMap<String, String>();
		values.put("test-key", "test-value");
		// int facId = randInt(1, 1000);
		int facId = 213;
		long eventTimeStamp = Calendar.getInstance().getTimeInMillis();

		cassandraContext = new ClassPathXmlApplicationContext(
				"classpath*:cassandra_context.xml");
		CQLEventRecordDAO cqlEventRecordDAO = (CQLEventRecordDAO) cassandraContext
				.getBean("cqlEventRecordDAOImpl");
		EventRecord eventRecord;
		System.out.print("Generating data ");
		String date = Calendar.getInstance().getTime().getMonth() + "/" + Calendar.getInstance().getTime().getDate() + "/" + (Calendar.getInstance().getTime().getYear() + 1900);
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
			eventRecord.setUserLogin("JaneDoe");
			eventRecord.setUserName("Jane Doe");
			eventRecord.setModule(PatientDataEnum.forValue(randPatientEnum())
					.getName());
			eventRecord.setSpecificItem(PatientDataEnum.forValue(
					randPatientEnum()).getName());
			if (eventRecord.getAction() == AuditActionEnum.DELETE) {
				eventRecord
						.setState("Consent Given#Consented##Consent Confirm By#pcc-ghalim##Consent Confirm Date#" +  date
								+ "##Route of Administration#by mouth##Education Provided To Resident#No##Date And Time of Administration#" + date);
			} else if (eventRecord.getAction() == AuditActionEnum.UPDATE) {
				eventRecord
						.setState("Resolved Date#" + date + "#" + date + "#Reaction Notes#Resolved By Jane Doe#Note");
			}

			try {
				cqlEventRecordDAO.persist(eventRecord);
				System.out.print(".");
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		return true;
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
