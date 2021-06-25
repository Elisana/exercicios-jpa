package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class RemoverUsuario {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");		
		EntityManager em = emf.createEntityManager();
		
		Usuario usuario = em.find(Usuario.class, 5L); //mapeia pa long
		
		//verifica se o usuário não está nulo
		if(usuario != null) {
			em.getTransaction().begin(); //abre a transação só se o usuário não estiver nulo
			em.remove(usuario);
			em.getTransaction().commit();
		}
		
		em.close();
		emf.close();
	}

}
