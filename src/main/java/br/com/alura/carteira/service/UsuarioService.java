package br.com.alura.carteira.service;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	
	public Page<UsuarioDto> Listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		
		return usuarios
				.map(t ->modelMapper.map(t, UsuarioDto.class));
	}
	@Transactional
	public UsuarioDto Cadastrar(UsuarioFormDto dto) {
		
		
		Usuario usuario = modelMapper.map(dto, Usuario.class);	
		
		String senha = new Random().nextInt(99999) +"" ;
		usuario.setSenha(senha);	
		usuarioRepository.save(usuario);
		
		return modelMapper.map(usuario, UsuarioDto.class);	
		
	}
}
