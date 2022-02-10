package br.com.alura.carteira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;


@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

	@Mock
	private TransacaoRepository transacaoRepository;

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private TransacaoService service; 
	
	@Test
	void deveriaCadastrarUmaTransacao() {
		
		TransacaoFormDto formDto = new TransacaoFormDto(
				"ITSA4",
				new BigDecimal("10.45"),
				120,
				TipoTransacao.COMPRA,
				LocalDate.now(),
				4l
				);

		TransacaoDto dto = service.Cadastrar(formDto);
		
		Mockito.verify(transacaoRepository.save(Mockito.any()));
		
		assertEquals(formDto.getTicker(), dto.getTicker());
		assertEquals(formDto.getPreco(), dto.getPreco());
		assertEquals(formDto.getQuantidade(), dto.getQuantidade());
		assertEquals(formDto.getTipo(), dto.getTipo());
		
		
	}
	@Test
	void naoDeveriaCadastrarUmaTransacaoComUsuarioInexistente() {
		
		TransacaoFormDto formDto = new TransacaoFormDto(
				"ITSA4",
				new BigDecimal("10.45"),
				120,
				TipoTransacao.COMPRA,
				LocalDate.now(),
				999999l
				);

		
		
		 Mockito.when(usuarioRepository
				 .getById(formDto.getUsuarioId()))
		 .thenThrow(EntityNotFoundException.class);
		 
		assertThrows(IllegalArgumentException.class, () -> service.Cadastrar(formDto));
		
		
		
		
	}
}
