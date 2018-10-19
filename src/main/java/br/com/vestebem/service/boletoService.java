package br.com.vestebem.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.vestebem.model.PagamentoBoleto;

@Service
public class boletoService {

	public void preencherPagamentoComBoleto(PagamentoBoleto pagamento, Date instante) {
		Calendar cal =  Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(cal.getTime());
		
	}

}
