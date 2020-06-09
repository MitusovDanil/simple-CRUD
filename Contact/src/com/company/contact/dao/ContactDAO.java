package com.company.contact.dao;

import java.util.List;

import com.company.contact.model.Contact;

public interface ContactDAO {
	int save(Contact contact);

	int update(Contact contact);

	Contact getContact(Integer id);

	int delete(Integer id);

	List<Contact> getAllContacts();

}
