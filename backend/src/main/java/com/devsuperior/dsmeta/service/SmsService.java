package com.devsuperior.dsmeta.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositoires.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private SaleRepository saleRepository;

	public void enviarSms(Long saleId) {
		
		Sale sale = saleRepository.findById(saleId).get();
		
		String data = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		
		String msg = "Vendendor " + sale.getSellerName() + " foi destaque em " + data + " com um total de R$ " + new DecimalFormat("#,##0.00").format(sale.getAmount());

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber remetente = new PhoneNumber(twilioPhoneTo);
		PhoneNumber destinatario = new PhoneNumber(twilioPhoneFrom);

		Message mensagem = Message.creator(remetente, destinatario, msg).create();

		System.out.println(mensagem.getSid());
	}
}