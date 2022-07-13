package com.devsuperior.dsmeta.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositoires.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public Page<Sale> listaVendas(String minDate, String maxDate, Pageable pageable) {
		
		LocalDate dataAtual = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate min = minDate.equals("") ? dataAtual.minusDays(365) : LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("") ? dataAtual : LocalDate.parse(maxDate);
		
		return repository.findSales(min, max, pageable);
	}
}
