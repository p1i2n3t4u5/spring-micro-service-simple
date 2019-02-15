package com.stockserviceapp.stock.stockservice.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Quote implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String quote;
	private BigDecimal price;

	public Quote(String quote, BigDecimal price) {
		this.quote = quote;
		this.price = price;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Quote [quote=" + quote + ", price=" + price + "]";
	}

}
