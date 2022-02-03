package br.com.alura.carteira.controller;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.service.TransacaoService;

//@Controller
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	@Autowired
	private TransacaoService service;
	
	@GetMapping	
	public Page<TransacaoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
		return service.Listar(paginacao);
	
	}
	@PostMapping
	public ResponseEntity<TransacaoDto> cadastrar(@RequestBody @Valid TransacaoFormDto dto,
			UriComponentsBuilder uriBuilder) {
		 TransacaoDto transacaoDto = service.Cadastrar(dto);
		 
		 
		 URI uri = uriBuilder.path("/transacoes/{id}").buildAndExpand(transacaoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(transacaoDto);
		
	}
	
}
