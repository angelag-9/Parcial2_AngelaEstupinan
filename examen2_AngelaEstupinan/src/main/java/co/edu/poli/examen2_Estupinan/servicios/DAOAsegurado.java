package co.edu.poli.examen2_Estupinan.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.examen2_Estupinan.servicios.ConexionBD;
import co.edu.poli.examen2_Estupinan.modelo.Asegurado;

public class DAOAsegurado implements CRUD<Asegurado> {

	@Override
	public String create(Asegurado s) {
		return null;
	}

	@Override
	public <K> Asegurado readone(K id) throws Exception {
		return null;
	}

	@Override
	public List<Asegurado> readall() throws Exception {

		Connection con = ConexionBD.getInstancia().getConexion();
		List<Asegurado> lista = new ArrayList<>();

		String SQL_SELECT_ASEGURADO = "SELECT id, nombre FROM asegurado;";

		PreparedStatement ps = con.prepareStatement(SQL_SELECT_ASEGURADO);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Asegurado s = new Asegurado(rs.getString("id"), rs.getString("nombre"));
			lista.add(s);
		}
		return lista;
	}
}

