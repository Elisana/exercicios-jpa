package teste.basico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.basico.Usuario;

public class ObterUsuarios {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");		
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select u from Usuario u";//" no isql = select u.* from Usuario u"
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setMaxResults(5); //limita o retorno a 5 linhas
		
		//Aqui ele executa a consulta montada acima
		List<Usuario> usuarios = query.getResultList();
		
		/*pode fazer tudo em uma linha, assim:
		  List<Usuario> usuarios = em
		  				.createQuery("select u from Usuario u", Usuario.class)
		  				.setMaxResults(5)
		  				.getResultList();
		  
		 * */ 
		
		for(Usuario usuario: usuarios) {
			System.out.println("ID: " + usuario.getId() + ", Email: " + usuario.getEmail());
		}
		
		em.close();
		emf.close();
	}

}
