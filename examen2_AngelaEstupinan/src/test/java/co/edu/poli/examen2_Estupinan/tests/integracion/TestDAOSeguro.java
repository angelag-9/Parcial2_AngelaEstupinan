package co.edu.poli.examen2_Estupinan.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Estupinan.modelo.SeguroDeVida;
import co.edu.poli.examen2_Estupinan.modelo.SeguroDeVehiculo;
import co.edu.poli.examen2_Estupinan.modelo.Seguro;
import co.edu.poli.examen2_Estupinan.modelo.Asegurado;
import co.edu.poli.examen2_Estupinan.servicios.DAOSeguro;

public class TestDAOSeguro {

	DAOSeguro dao = new DAOSeguro();

	@Test
	void create_seguroDeVida_y_readone() throws Exception {

		Asegurado asegurado = new Asegurado("T001", "Test");

		SeguroDeVida seguroDeVida = new SeguroDeVida("999001", "12/2025", true, asegurado, "Angela E");

		String result = dao.create(seguroDeVida);

		assertTrue(result.contains("guardada"));

		Seguro s = dao.readone("999001");

		assertNotNull(s);
		assertTrue(s instanceof SeguroDeVida);

		SeguroDeVida d = (SeguroDeVida) s;
		assertEquals("Angela E", d.getBeneficiario());
	}

	@Test
	void create_seguroDeVehiculo_y_readone() throws Exception {

		Asegurado asegurado = new Asegurado("T001", "Test");

		SeguroDeVehiculo seguroDeVehiculo = new SeguroDeVehiculo("999002", "12/2025", true, asegurado, "Honda");

		String result = dao.create(seguroDeVehiculo);

		assertTrue(result.contains("guardada"));

		Seguro s = dao.readone("999002");

		assertNotNull(s);
		assertTrue(s instanceof SeguroDeVehiculo);

		SeguroDeVehiculo c = (SeguroDeVehiculo) s;
		assertEquals("Honda", c.getMarca());
	}

	@Test
	void readone_noExiste() throws Exception {

		Seguro s = dao.readone("000000");

		assertNull(s);
	}
}