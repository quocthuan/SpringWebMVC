package vn.com.tma.dao;

import java.util.List;

import vn.com.tma.model.Contact;

public interface IContactDao {
	public void insert(Contact contact);
	public void delete(String contactId);
	public Contact findContactById(int contactId);
	public List<Contact> loadAllContacts();
}
