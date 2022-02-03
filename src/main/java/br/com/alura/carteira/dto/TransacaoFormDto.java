package br.com.alura.carteira.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.alura.carteira.modelo.TipoTransacao;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter 
@Setter
public class TransacaoFormDto {

	@NotBlank
	@Size(min = 5, max=6)
	@Pattern(regexp="[a-zA-Z]{4}[0-9][0-9]?", message= "{transacao.ticker.invalido}")
	private String ticker;
	

	@DecimalMin("0.01")
	private BigDecimal preco;
	
	@NotNull
	private int quantidade;
	
	@NotNull
	private TipoTransacao tipo;
	
	@NotNull
	@PastOrPresent
	private LocalDate data;
	
	@JsonAlias("usuario_id")
	@NotNull
	private Long usuarioId;

	
}
