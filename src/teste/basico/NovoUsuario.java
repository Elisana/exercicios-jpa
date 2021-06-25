package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class NovoUsuario {
	public static void main(String[] args) {
		
		//EntityManager = Fabrica de gerente de entidade
		//O papel dele � criar um EntityManager
		//Ele tem encapsulado a conex�o com o banco de dados. Ent�o, se a aplica��o conectar com mais de um
		//banco de dados, precisa ter mais de um EntityManagerFactory
		//passa o nome do banco de dados. Est� informado no persistence.xml: <persistence-unit name="exercicios-jpa">
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		
		//respons�vel por fazer o crud ao banco de dados
		//� o gerenciador das entidades 
		//� a classe que nos ajuda a persistir os dados no Banco de Dados
		EntityManager em = emf.createEntityManager();
		
		Usuario novoUsuario = new Usuario("Carolina","carol@lanche.com.br");
				
		em.getTransaction().begin(); //inicia uma transa��o de banco
		em.persist(novoUsuario); //m�todo que persite no banco
		em.getTransaction().commit();//grava no banco de dados
		
		System.out.println("O Id gerado foi: " + novoUsuario.getId());
		
		em.close();
		emf.close();
		
	}
}
