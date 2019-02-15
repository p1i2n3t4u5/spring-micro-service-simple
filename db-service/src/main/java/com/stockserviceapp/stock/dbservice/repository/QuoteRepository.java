package com.stockserviceapp.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockserviceapp.stock.dbservice.entity.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
	
	List<Quote> findByUserName(String userName);

}
