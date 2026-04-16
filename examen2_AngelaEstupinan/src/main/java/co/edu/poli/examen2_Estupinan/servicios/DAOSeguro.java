package co.edu.poli.examen2_Estupinan.servicios;

import co.edu.poli.examen2_Estupinan.modelo.SeguroDeVida;
import co.edu.poli.examen2_Estupinan.modelo.SeguroDeVehiculo;
import co.edu.poli.examen2_Estupinan.modelo.Seguro;
import co.edu.poli.examen2_Estupinan.modelo.Asegurado;
import co.edu.poli.examen2_Estupinan.servicios.CRUD;
import co.edu.poli.examen2_Estupinan.servicios.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOSeguro implements CRUD<Seguro> {

	@Override
	public String create(Seguro s) throws Exception {

	    Connection con = ConexionBD.getInstancia().getConexion();
	    con.setAutoCommit(false);

	    // ✅ NUEVO: Insertar asegurado primero si no existe
	    String SQL_INSERT_ASEGURADO = "INSERT IGNORE INTO asegurado (id, nombre) VALUES (?, ?)";
	    PreparedStatement psA = con.prepareStatement(SQL_INSERT_ASEGURADO);
	    psA.setString(1, s.getAsegurado().getId());
	    psA.setString(2, s.getAsegurado().getNombre());
	    psA.executeUpdate();

	    // El resto queda igual...
	    String SQL_INSERT_SEGURO = "INSERT INTO seguro (numero, fecha_exp, estado, asegurado_id) VALUES (?, ?, ?, ?)";
	    PreparedStatement ps = con.prepareStatement(SQL_INSERT_SEGURO);
	    ps.setString(1, s.getNumero());
	    ps.setString(2, s.getFechaExp());
	    ps.setBoolean(3, s.isEstado());
	    ps.setString(4, s.getAsegurado().getId());
	    ps.executeUpdate();

	    String SQL_INSERT_VIDA = "INSERT INTO seguro_vida (numero, beneficiario) VALUES (?, ?)";
	    String SQL_INSERT_VEHICULO = "INSERT INTO seguro_vehiculo (numero, marca) VALUES (?, ?)";

	    String sql = (s instanceof SeguroDeVida) ? SQL_INSERT_VIDA : SQL_INSERT_VEHICULO;

	    ps = con.prepareStatement(sql);
	    ps.setString(1, s.getNumero());

	    if (s instanceof SeguroDeVida)
	        ps.setString(2, ((SeguroDeVida) s).getBeneficiario());
	    else
	        ps.setString(2, ((SeguroDeVehiculo) s).getMarca());

	    try {
	        ps.executeUpdate();
	        con.commit();
	        return "✔ " + s.getClass().getSimpleName() + " [" + s.getNumero() + "] guardada correctamente.";
	    } catch (Exception e) {
	        con.rollback();
	        return e.getMessage();
	    } finally {
	        con.setAutoCommit(true);
	    }
	}

	@Override
	public <K> Seguro readone(K num) throws Exception {

		Connection con = ConexionBD.getInstancia().getConexion();

		String SQL_SELECT_VIDA = "SELECT s.numero, s.fecha_exp, s.estado, "
				+ "a.id AS asegurado_id, a.nombre AS asegurado_nombre, " + "v.beneficiario " + "FROM seguro_vida v "
				+ "INNER JOIN seguro s ON v.numero = s.numero " + "INNER JOIN asegurado a ON s.asegurado_id = a.id "
				+ "WHERE v.numero = ?";

		PreparedStatement ps = con.prepareStatement(SQL_SELECT_VIDA);
		ps.setString(1, (String) num);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new SeguroDeVida(rs.getString("numero"), rs.getString("fecha_exp"), rs.getBoolean("estado"),
					new Asegurado(rs.getString("asegurado_id"), rs.getString("asegurado_nombre")),
					rs.getString("beneficiario"));
		}

		String SQL_SELECT_VEHICULO = "SELECT s.numero, s.fecha_exp, s.estado, "
				+ "a.id AS asegurado_id, a.nombre AS asegurado_nombre, " + "v.marca " + "FROM seguro_vehiculo v "
				+ "INNER JOIN seguro s ON v.numero = s.numero " + "INNER JOIN asegurado a ON s.asegurado_id = a.id "
				+ "WHERE v.numero = ?";

		ps = con.prepareStatement(SQL_SELECT_VEHICULO);
		ps.setString(1, (String) num);
		rs = ps.executeQuery();
		if (rs.next()) {
			return new SeguroDeVehiculo(rs.getString("numero"), rs.getString("fecha_exp"), rs.getBoolean("estado"),
					new Asegurado(rs.getString("asegurado_id"), rs.getString("asegurado_nombre")),
					rs.getString("marca"));
		}

		return null;
	}

	@Override
	public List<Seguro> readall() {
		return null;
	}
}
