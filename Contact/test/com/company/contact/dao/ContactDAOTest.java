package com.company.contact.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.company.contact.model.Contact;

class ContactDAOTest {
	
	private DriverManagerDataSource dataSource;
	private ContactDAO dao;
	
	@BeforeEach
	void setUpBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/crud_app");
		dataSource.setUsername("minus");
		dataSource.setPassword("AppForNauka$315920");
		
		dao = new ContactDAOImpl(dataSource);
	}

	@Test
	void testSave() {
		Contact contact = new Contact("Danil", "danil@mail.ru", "St Petersburg 2", "9992020462");
		int save = dao.save(contact);
		assertTrue(save > 0); 
	}

	@Test
	void testUpdate() {
		Contact contact = new Contact(2, "Vika", "vika@mail.ru", "St Petersburg 2", "9991231212");
		int update = dao.update(contact);
		
		assertTrue(update > 0);
	}

	@Test
	void testGetContact() {
		Integer id = 1;
		Contact contact = dao.getContact(id);
		
		assertNotNull(contact);
	}

	@Test
	void testDelete() {
		Integer id = 2;
		int result = dao.delete(id);
		
		assertTrue(result > 0);
		
	}

	@Test
	void testGetAllContacts() {
		List<Contact> listContacts = dao.getAllContacts();
		
		assertTrue(listContacts != null);
	}

}
