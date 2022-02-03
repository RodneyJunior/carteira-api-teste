package br.com.alura.carteira.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacoes")
public class Transacao {
	//public Transacao(long id2, String ticker2, LocalDate now, BigDecimal bigDecimal, int i, TipoTransacao compra,
	//		Usuario usuario2) {
		// TODO Auto-generated constructor stub
	//}

	//@Column(name="tck")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ticker;
	private BigDecimal preco;
	private Integer quantidade;
	private LocalDate data;
	
	//notação do jackson
	//@JsonIgnore ignora sempre o atributo
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	
	@ManyToOne
	//@JoinColumn(name="id_column")
	private Usuario usuario;
}
