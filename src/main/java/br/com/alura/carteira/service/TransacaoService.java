package br.com.alura.carteira.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;

@Service
public class TransacaoService {

	private ModelMapper modelMapper = new ModelMapper();
	private List<Transacao> transacoes = new ArrayList<>();
	
	
	public List<TransacaoDto> Listar() {
		return transacoes
				.stream()
				.map(t ->modelMapper.map(t, TransacaoDto.class))
				.collect(Collectors.toList());
		
	}
	
	public String Cadastrar(TransacaoFormDto dto)
	{
	//valid informa que o validation deve ser executado na classe de domínio
			Transacao t = modelMapper.map(dto, Transacao.class);
			transacoes.add(t);
			return "cadastro Ok";
	}
}
