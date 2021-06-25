package modelo.muitospramuitos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "filmes")
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Double nota;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			 name = "atores_filmes",
			 joinColumns = @JoinColumn(name="filme_id", referencedColumnName = "id"), //referente a classe que estou agora
			 inverseJoinColumns = @JoinColumn(name="ator_id", referencedColumnName = "id") //referente a outra classe
			 ) //especifica o nome da tabela que ser� criada para jun��o dos dados nXn 
	private List<Ator> atores;

	public Filme() {
	}

	public Filme(String nome, Double nota) {
		super();
		this.nome = nome;
		this.nota = nota;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public List<Ator> getAtores() {
		if(atores == null) {
			atores = new ArrayList<>();
		}
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}
	
	//aqui, garante o relacionamento bidirecional
	public void adicionarAtor(Ator ator) {
		//se o objeto ator n�o for nulo, e o ator n�o existir na lista existente
		if(ator != null && !getAtores().contains(ator)) {
			getAtores().add(ator);
			
			//Se o filme atual n�o est� na lsita de filmes do ator
			if (!ator.getFilmes().contains(this)) {
				ator.getFilmes().add(this);
			}
		}
	}

}
