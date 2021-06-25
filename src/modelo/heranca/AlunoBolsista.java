package modelo.heranca;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AB") // aluno boslista
public class AlunoBolsista extends Aluno {

	private double ValorBolsa;

	public AlunoBolsista() {
	}

	public AlunoBolsista(Long matricula, String nome,  double valorBolsa) {
		super(matricula, nome);
		ValorBolsa = valorBolsa;
	}

	public double getValorBolsa() {
		return ValorBolsa;
	}

	public void setValorBolsa(double valorBolsa) {
		ValorBolsa = valorBolsa;
	}

	
}
