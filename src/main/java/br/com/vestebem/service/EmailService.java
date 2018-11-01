package br.com.vestebem.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.model.Pedido;

public interface EmailService {
	public void sendOrderConfirmationEmail(Pedido pedido);

	public void sendEmail(SimpleMailMessage msg);

	public void sendNewPasswordEmail(Cliente cliente, String newPass);

}
