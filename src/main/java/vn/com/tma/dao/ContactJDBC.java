package vn.com.tma.dao;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.com.tma.model.Contact;

public class ContactJDBC implements IContactDao {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Contact contact) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO contact " + "(name, age, address) VALUES (?, ?, ?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setInt(2, contact.getAge());
			ps.setString(3, contact.getAddress());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public void delete(int contactId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM contact WHERE contactid = " + contactId;
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public void update(Contact contact) {
		// TODO Auto-generated method stub
		String sql = "UPDATE contact SET name = ?, age = ?, address = ? WHERE contactid = ?";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setInt(2, contact.getAge());
			ps.setString(3, contact.getAddress());
			ps.setInt(4, contact.getContactId());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public Contact findContactById(int contactId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM contact WHERE contactId = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, contactId);
			Contact contact = null;
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				contact = new Contact(rs.getInt("contactId"), rs.getString("name"), rs.getInt("age"), rs.getString("address"));
			}
			
			rs.close();
			ps.close();
			return contact;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public List<Contact> loadAllContacts() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM contact";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Contact contact = null;
			ResultSet rs = ps.executeQuery();
			
			List<Contact> contactList = new ArrayList<Contact>();
			
			while(rs.next()) {
				contact = new Contact(rs.getInt("contactId"), rs.getString("name"), rs.getInt("age"), rs.getString("address"));
				contactList.add(contact);
			}
			
			rs.close();
			ps.close();
			return contactList;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
