package co.edu.poli.examen2_Estupinan.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Estupinan.modelo.SeguroDeVida;
import co.edu.poli.examen2_Estupinan.modelo.Seguro;
import co.edu.poli.examen2_Estupinan.modelo.Asegurado;

public class TestSeguro {

	@Test
	void bloquear_cambiaEstadoAFalso() {
		Asegurado asegurado = new Asegurado("1", "Test");

		Seguro s = new SeguroDeVida("123", "12/2025", true, asegurado, "Gineth");

		String mensaje = s.bloquear();

		assertFalse(s.isEstado());
		assertTrue(mensaje.contains("BLOQUEADA"));
	}

	@Test
	void activar_cambiaEstadoAVerdadero() {
		Asegurado asegurado = new Asegurado("1", "Test");

		Seguro s = new SeguroDeVida("123", "12/2025", false, asegurado, "Gineth");

		String mensaje = s.activar();

		assertTrue(s.isEstado());
		assertTrue(mensaje.contains("ACTIVADA"));
	}

	@Test
	void getters_retornaValoresCorrectos() {
		Asegurado asegurado = new Asegurado("1", "Test");

		Seguro s = new SeguroDeVida("123", "12/2025", true, asegurado, "Gineth");

		assertEquals("123", s.getNumero());
		assertEquals("12/2025", s.getFechaExp());
		assertTrue(s.isEstado());
		assertEquals(asegurado, s.getAsegurado());
	}

	@Test
	void setters_modificanValores() {
		Asegurado asegurado = new Asegurado("1", "Test");
		Asegurado nuevo = new Asegurado("2", "Nuevo");

		Seguro s = new SeguroDeVida("123", "12/2025", true, asegurado, "Gineth");

		s.setNumero("999");
		s.setFechaExp("01/2030");
		s.setEstado(false);
		s.setAsegurado(nuevo);

		assertEquals("999", s.getNumero());
		assertEquals("01/2030", s.getFechaExp());
		assertFalse(s.isEstado());
		assertEquals(nuevo, s.getAsegurado());
	}

	@Test
	void toString_contieneDatos() {
		Asegurado asegurado = new Asegurado("1", "Test");

		Seguro s = new SeguroDeVida("123", "12/2025", true, asegurado, "Gineth");

		String texto = s.toString();

		assertTrue(texto.contains("123"));
		assertTrue(texto.contains("12/2025"));
	}
}
