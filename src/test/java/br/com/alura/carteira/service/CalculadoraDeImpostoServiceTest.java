package br.com.alura.carteira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.junit.jupiter.api.Test;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;

class CalculadoraDeImpostoServiceTest {

	@Test
	void transacaoDoTipoCompraNaoDeveriaTerImposto() {
		Transacao transacao = new Transacao(
				120l,
				"BBSE3",
				new BigDecimal("30.00"),
				10,
				LocalDate.now(),
				TipoTransacao.COMPRA,
				new Usuario(1l, "Rafaela", "rafaela@gmail.com", "12345")
				);
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(BigDecimal.ZERO, imposto);
		
	}	
	@Test
	void transacaoDoTipoVendaComValorMenoVinteMilNaoDeveriaTerImposto() {
		Transacao transacao = new Transacao(
				120l,
				"BBSE3",
				new BigDecimal("30.00"),
				10,
				LocalDate.now(),
				TipoTransacao.VENDA,
				new Usuario(1l, "Rafaela", "rafaela@gmail.com", "12345")
				);
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(BigDecimal.ZERO, imposto);
		
	}
	
	@Test
	void transacaoDoTipoVendaComValorMaiorQueVinteMiDeveriaTerImpostoDeQuinzePorcento() {
		Transacao transacao = new Transacao(
				120l,
				"BBSE3",
				new BigDecimal("1000.00"),
				30,
				LocalDate.now(),
				TipoTransacao.VENDA,
				new Usuario(1l, "Rafaela", "rafaela@gmail.com", "12345")
				);
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(new BigDecimal("4500.00"), imposto);
		
	}	
}
