package com.jcuentas.example.data.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "accounts")
public class Account {

	// for QueryBuilder to be able to find the fields
	public static final String NAME_FIELD_NAME = "name";
	public static final String PASSWORD_FIELD_NAME = "passwd";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
	private String name;

	@DatabaseField(columnName = PASSWORD_FIELD_NAME)
	private String password;

	@ForeignCollectionField
	private ForeignCollection<Order> orders;

	Account() {
		// all persisted classes must define a no-arg constructor with at least package visibility
	}

	public Account(String name) {
		this.name = name;
	}

	public Account(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ForeignCollection<Order> getOrders() {
		return orders;
	}

	public void setOrders(ForeignCollection<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		return name.equals(((Account) other).name);
	}
}
