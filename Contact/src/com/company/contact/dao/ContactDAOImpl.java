package com.company.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.company.contact.model.Contact;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;

	public ContactDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Contact contact) {
		String insertSql = "INSERT INTO crud_app.contact (name, email, address, phone) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(insertSql, contact.getName(), contact.getEmail(), contact.getAddress(),
				contact.getPhone());

	}

	@Override
	public int update(Contact contact) {
		String updateSql = "UPDATE crud_app.contact SET name=?, email=?, address=?, phone=? WHERE contact_id=?";
		return jdbcTemplate.update(updateSql, contact.getName(), contact.getEmail(), contact.getAddress(),
				contact.getPhone(), contact.getId());
	}

	@Override
	public Contact getContact(Integer id) {
		String getSql = "SELECT * FROM crud_app.contact WHERE contact_id = " + id;
		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String phone = rs.getString("phone");

					return new Contact(id, name, email, address, phone);
				}
				return null;
			}

		};
		return jdbcTemplate.query(getSql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String deleteSql = "DELETE FROM crud_app.contact WHERE contact_id =" + id;
		return jdbcTemplate.update(deleteSql);
	}

	@Override
	public List<Contact> getAllContacts() {
		String getAll = "SELECT * FROM crud_app.contact";

		RowMapper<Contact> rowMapper = new RowMapper<Contact>() {
			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("contact_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone = rs.getString("phone");

				return new Contact(id, name, email, address, phone);
			}

		};
		return jdbcTemplate.query(getAll, rowMapper);
	}

}
