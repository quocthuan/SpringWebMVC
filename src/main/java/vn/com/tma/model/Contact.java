package vn.com.tma.model;

public class Contact {
	private int contactId;
	private String name;
	private int age;
	private String address;
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	public Contact(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public Contact(int contactId, String name, int age, String address) {
		this(name, age, address);
		this.contactId = contactId;
	}
	/**
	 * @return the contactId
	 */
	public int getContactId() {
		return contactId;
	}
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
