package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//Classe generics
public class DAO<E> {
	private static EntityManagerFactory emf; //1 por banco de dados
	private EntityManager em;       
	private Class<E> classe;
	
	//bloco estático. é carregado pelo Java ao instanciar a classe
	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		} catch (Exception e) {
			// logar -> log4j
		}	
	}
	
	public DAO() {
		this(null);
	}
	
	//vai receber uma classe
	public DAO(Class<E> classe) {
		this.classe = classe;
		//inicializa o EntityManager no construtor
		em = emf.createEntityManager();
	}
	
	//método publico para abrir uma transação
	public DAO<E> abrirT(){
		em.getTransaction().begin();
		return this;
	}
	
	//método publico para fechar uma transação
	public DAO<E> fecharT(){
		em.getTransaction().commit();
		return this;
	}
	
	//método publico para incluir
	public DAO<E> incluir(E entidade){
		em.persist(entidade);
		return this;
	}

	//método publico para incluir - Atômico (abre, inclui e fecha
 	public DAO<E> incluirAtomico(E entidade){
		return this.abrirT().incluir(entidade).fecharT();		
	}
 	
 	//retorna E = entidade
 	public E obterPorID(Object id) {
 		return em.find(classe, id); 		
 	}
 	
 	public List<E> obterTodos(){ 		
 		return this.obterTodos(10, 0);
 	}
 	 
 	public List<E> obterTodos(int qtde, int deslocamento){ 		
 		if(classe == null) {
 			throw new UnsupportedOperationException("Classe nula.");
 		}
 		
 		String jpql = "select e from " + classe.getName() + " e"; 		
 		TypedQuery<E> query = em.createQuery(jpql, classe);
 		query.setMaxResults(qtde);
 		query.setFirstResult(deslocamento); 		
 		return query.getResultList();
 	}
 	
 	public List<E> consultar(String nomeConsulta, Object... params){
 		TypedQuery<E> query = em.createNamedQuery(nomeConsulta, classe);
 		for (int i = 0; i < params.length; i+=2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		} 
 		return query.getResultList();
 	}
 	
 	public E consultarUm(String nomeConsulta, Object... params) {
 		List<E> lista = consultar(nomeConsulta, params);
 		return lista.isEmpty() ? null : lista.get(0);
 	}
 	
 	public void fechar() {
 		em.close();
 	}
		
}
