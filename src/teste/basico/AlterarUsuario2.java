package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;
/* Estado Gerenciado: o JPA faz um gerenciamento sobre o objeto, e qualquer altera��o feita ser� sicronizada com o banco de
 * dados, mesmo sem usar o comando merge */
public class AlterarUsuario2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		//busca o usu�rio que ser� alterado
		Usuario usuario = em.find(Usuario.class, 7L);
		System.out.println("antes: " + usuario.getNome());
		usuario.setNome("Carolina Henrique Darolt teste");
				
		em.merge(usuario); //o merge � para alterar uma informa��o que j� existe.
		//em.getTransaction().commit();
		
		System.out.println("depois: " + usuario.getNome());
		emf.close();
		em.close();
	}

}
