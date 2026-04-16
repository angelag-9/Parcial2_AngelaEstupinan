package co.edu.poli.examen2_Estupinan.modelo;

public class SeguroDeVehiculo extends Seguro {

	private String marca;

	public SeguroDeVehiculo(String numero, String fechaExp, boolean estado, Asegurado asegurado, String marca) {
		super(numero, fechaExp, estado, asegurado);
		this.setMarca(marca);
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "SeguroDeVehiculo [marca=" + marca + "]";
	}

}
