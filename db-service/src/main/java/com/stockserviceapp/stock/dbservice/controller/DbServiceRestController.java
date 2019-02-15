package com.stockserviceapp.stock.dbservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockserviceapp.stock.dbservice.entity.Quote;
import com.stockserviceapp.stock.dbservice.model.Quotes;
import com.stockserviceapp.stock.dbservice.repository.QuoteRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceRestController {

	@Autowired
	QuoteRepository quoteRepository;

	@GetMapping("/{username}")
	private List<String> getQuotes(@PathVariable("username") String username) {

		// quoteRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());

		return getQuotesByName(username);
	}

	private List<String> getQuotesByName(String username) {
		return quoteRepository.findByUserName(username).stream().map(quote -> {
			return quote.getQuote();
		}).collect(Collectors.toList());
	}

	@PostMapping("/add")
	private List<String> add(@RequestBody Quotes quotes) {
		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(), quote))
				.forEach(quote -> quoteRepository.save(quote));

		return getQuotesByName(quotes.getUserName());

	}

	@DeleteMapping("/delete/{username}")
	private List<String> delete(@PathVariable("username") String username) {
		System.out.println("username:" + username);
		List<Quote> quotes = quoteRepository.findByUserName(username);
		quoteRepository.deleteAll(quotes);
		return getQuotesByName(username);

	}

}
