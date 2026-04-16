package co.edu.poli.examen2_Estupinan.modelo;

public abstract class Seguro {

	private String numero;

	private String fechaExp;

	private boolean estado;

	private Asegurado asegurado;

	public Seguro(String numero, String fechaExp, boolean estado, Asegurado asegurado) {
		this.setNumero(numero);
		this.setFechaExp(fechaExp);
		this.setEstado(estado);
		this.setAsegurado(asegurado);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Asegurado getAsegurado() {
		return asegurado;
	}

	public void setAsegurado(Asegurado asegurado) {
		this.asegurado = asegurado;
	}

	public String bloquear() {
		this.estado = false;
		return "Tarjeta " + numero + " BLOQUEADA.";
	}

	public String activar() {
		this.estado = true;
		return "Tarjeta " + numero + " ACTIVADA.";
	}

	@Override
	public String toString() {
		return "Seguro [numero=" + numero + ", fechaExp=" + fechaExp + ", estado=" + estado + ", asegurado=" + asegurado
				+ "]";
	}

}
