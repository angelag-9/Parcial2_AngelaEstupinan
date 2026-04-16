package co.edu.poli.examen2_Estupinan.modelo;

public class Asegurado {

	private String id;

	private String nombre;

	public Asegurado(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Asegurado [id=" + id + ", nombre=" + nombre + "]";
	}

}
