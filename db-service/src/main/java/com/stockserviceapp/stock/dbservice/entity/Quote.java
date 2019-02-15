package com.stockserviceapp.stock.dbservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Quote")
@Table(name = "quote")
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "quote")
	private String quote;

	public Quote() {
		super();
	}

	public Quote(String userName, String quote) {
		super();
		this.userName = userName;
		this.quote = quote;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", userName=" + userName + ", quote=" + quote + "]";
	}

}
