package br.com.alura.carteira.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;

@ExtendWith(SpringExtension.class)//usar o spring para entender o que é o Autowired
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) //Spring, não substitua o meu banco de dados pelo o que está em memória. usar a base da aplicação não a qeu está em memória 
@ActiveProfiles("teste") //spring considere que o profile ativo é o de teste
class TransacaoRepositoryTest {

	@Autowired
	private TransacaoRepository repository;
	
	@Autowired
	private TestEntityManager em; //Entity manager para teste semelhante da JPA porém do Spring
	
	@Test
	void deveriaRetornarRelatorioDeCarteiraDeInvestimentos() {
		Usuario usuario = new Usuario("Rafaela Boa", "rafaela", "123456");
		em.persist(usuario);
		
		Transacao t1 = new Transacao("ITSA4", 
				new BigDecimal("10.00"), 
				50,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);		
		em.persist(t1);
			
		Transacao t2 = new Transacao("BBSE3", 
				new BigDecimal("22.00"), 
				80,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);
		em.persist(t2);
		
		Transacao t3 = new Transacao("EGIE3", 
				new BigDecimal("40.00"), 
				25,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);		
		em.persist(t3);
		
		Transacao t4 = new Transacao("ITSA4", 
				new BigDecimal("11.20"), 
				40,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);		
		em.persist(t4);
		
		Transacao t5 = new Transacao("SAPR4", 
				new BigDecimal("04.02"), 
				120,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);	
		em.persist(t5);
		
		List<ItemCarteiraDto> relatorio = repository.relatorioCarteiraDeInvestimentos();
		//assertEquals(4,  relatorio.size());//asserts do junit
		//para realizar asserts mais complexos vamos usar a biblioteca assert j
		Assertions 
		.assertThat(relatorio)
		.hasSize(4)
		.extracting(ItemCarteiraDto::getTicker,ItemCarteiraDto::getQuantidade, ItemCarteiraDto::getPercentual)
		.containsExactlyInAnyOrder(
				Assertions.tuple("ITSA4", 90l, new BigDecimal ("28.57")),
				Assertions.tuple("BBSE3", 80l, new BigDecimal ("25.40")),
				Assertions.tuple("EGIE3", 25l, new BigDecimal ("7.94")),
				Assertions.tuple("SAPR4", 120l, new BigDecimal ("38.10"))
				);		
	}

	@Test
	void deveriaRetornarRelatorioDeCarteiraDeInvestimentosConsiderandoVendas() {
		Usuario usuario = new Usuario("Rafaela Boa", "rafaela", "123456");
		em.persist(usuario);
		
		Transacao t1 = new Transacao("ITSA4", 
				new BigDecimal("10.00"), 
				50,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);		
		em.persist(t1);
			
		Transacao t2 = new Transacao("BBSE3", 
				new BigDecimal("22.00"), 
				80,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);
		em.persist(t2);
		
		Transacao t3 = new Transacao("EGIE3", 
				new BigDecimal("40.00"), 
				25,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);		
		em.persist(t3);
		
		Transacao t4 = new Transacao("ITSA4", 
				new BigDecimal("11.20"), 
				40,
				LocalDate.now(), 
				TipoTransacao.VENDA,	   
				usuario);		
		em.persist(t4);
		
		Transacao t5 = new Transacao("SAPR4", 
				new BigDecimal("04.02"), 
				120,
				LocalDate.now(), 
				TipoTransacao.COMPRA,			   
				usuario);	
		em.persist(t5);
		
		List<ItemCarteiraDto> relatorio = repository.relatorioCarteiraDeInvestimentos();
		//assertEquals(4,  relatorio.size());//asserts do junit
		//para realizar asserts mais complexos vamos usar a biblioteca assert j
		Assertions 
		.assertThat(relatorio)
		.hasSize(4)
		.extracting(ItemCarteiraDto::getTicker,ItemCarteiraDto::getQuantidade, ItemCarteiraDto::getPercentual)
		.containsExactlyInAnyOrder(
				Assertions.tuple("ITSA4", 10l, new BigDecimal ("4.26")),
				Assertions.tuple("BBSE3", 80l, new BigDecimal ("34.04")),
				Assertions.tuple("EGIE3", 25l, new BigDecimal ("10.64")),
				Assertions.tuple("SAPR4", 120l, new BigDecimal ("51.06"))
				);		
	}
}
