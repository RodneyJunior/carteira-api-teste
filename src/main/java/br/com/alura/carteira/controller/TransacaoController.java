package br.com.alura.carteira.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.service.TransacaoService;

//@Controller
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	@Autowired
	private TransacaoService service;
	
	@GetMapping	
	public List<TransacaoDto> listar() {
		return service.Listar();
	
	}
	@PostMapping
	public String cadastrar(@RequestBody @Valid TransacaoFormDto dto) {
		return service.Cadastrar(dto);
		
	}
	
}
