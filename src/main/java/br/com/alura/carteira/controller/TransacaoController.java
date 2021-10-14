package br.com.alura.carteira.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;

//@Controller
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	private ModelMapper modelMapper = new ModelMapper();
	private List<Transacao> transacoes = new ArrayList<>();
	@GetMapping
	//@ResponseBody
	//String ticker, BigDecimal preco, int quantidade, LocalDate data,  TipoTransacao tipo)
	public List<TransacaoDto> listar() {
		List<TransacaoDto> transacaoDto = new ArrayList<>();
		
		/*for(Transacao transacao: transacoes) {
			TransacaoDto dto = new TransacaoDto();
			dto.setTicker(transacao.getTicker());
			dto.setPreco(transacao.getPreco());
			dto.setQuantidade(transacao.getQuantidade());
			dto.setTipo(transacao.getTipo());	
			transacaoDto.add(dto);
		} */
	
		
		
		//TransacaoDto DTO = modelMapper.map(transacao, TransacaoDto.class);
		
		return transacoes
				.stream()
				.map(t ->modelMapper.map(t, TransacaoDto.class))
				.collect(Collectors.toList());
		//return transacaoDto; //o spring chama jackson e preenche o objeto
	}
	@PostMapping
	public String cadastrar(@RequestBody @Valid TransacaoFormDto dto) {
		//valid informa que o validation deve ser executado na classe de domínio
		Transacao t = modelMapper.map(dto, Transacao.class);
		
		/*
		Transacao transacao = new Transacao(
				dto.getTicker(),
				dto.getPreco(),
				dto.getQuantidade(),
				dto.getData(),
				dto.getTipo()	
				);*/
		
		
		transacoes.add(t);
		
		
		return "cadastro Ok";
	}
	
}
