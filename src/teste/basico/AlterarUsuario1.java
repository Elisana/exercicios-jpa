package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario1 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		//busca o usuário que será alterado
		Usuario usuario = em.find(Usuario.class, 7L);
		System.out.println("antes: " + usuario.getNome());
		usuario.setNome("Carolina Henrique Darolt");
		usuario.setEmail("carolina@edamamae@com.br");
		
		em.merge(usuario); //o merge é para alterar uma informação que já existe.
		em.getTransaction().commit();
		
		System.out.println("depois: " + usuario.getNome());
		emf.close();
		em.close();
	}

}
