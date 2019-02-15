package com.stockserviceapp.stock.stockservice.restcontroller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stockserviceapp.stock.stockservice.models.Quote;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/rest/stock")
public class StockRestController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{username}")
	public List<Quote> name(@PathVariable("username") String username) {

		// List<String> quotes =
		// restTemplate.getForObject("http://localhost:8300/rest/db/" + username,
		// List.class);

//		ResponseEntity<List<String>> responseEntity = restTemplate.exchange("http://localhost:8300/rest/db/" + username,
//				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
//				});

		ResponseEntity<List<String>> responseEntity = restTemplate.exchange("http://db-service/rest/db/" + username,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});

		List<String> quotes = responseEntity.getBody();
		return quotes.stream().map(quote -> {
			Stock stock = getStockFromYahoo(quote);
			return new Quote(quote, stock.getQuote().getPrice());
		}).collect(Collectors.toList());

	}

	private Stock getStockFromYahoo(String quote) {
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
