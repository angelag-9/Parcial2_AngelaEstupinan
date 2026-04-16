package co.edu.poli.examen2_Estupinan.modelo;

public class SeguroDeVida extends Seguro {

	private String beneficiario;

	public SeguroDeVida(String numero, String fechaExp, boolean estado, Asegurado asegurado, String beneficiario) {
		super(numero, fechaExp, estado, asegurado);
		this.setBeneficiario(beneficiario);
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	@Override
	public String toString() {
		return super.toString() + ", SeguroDeVida [beneficiario=" + beneficiario + "]";
	}
}
