package teste.basico;

import infra.DAO;
import modelo.basico.Produto;

public class NovoProduto {
	public static void main(String[] args) {
		
		Produto produto = new Produto("Caneta", 7.45);
		
		DAO<Produto> dao = new DAO<>(Produto.class);
		dao.abrirT().incluir(produto).fecharT();
		
		
		Produto produto2 = new Produto("Notebook", 7053.45);
		dao.incluirAtomico(produto2);
		
		
		Produto produto3 = new Produto("Monitor", 2060.45);
		dao.incluirAtomico(produto3).fechar();
		
		System.out.println("Id do produto: " + produto3.getId());
		
	}
}
