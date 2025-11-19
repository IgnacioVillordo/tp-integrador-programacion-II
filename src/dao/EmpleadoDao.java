package dao;

import entities.Empleado;
import entities.Legajo;

import java.sql.Connection;
import java.sql.SQLException;

public interface EmpleadoDao extends GenericDao<Empleado> {
    void setLegajo(int id_empleado, Legajo legajo) throws SQLException;
    void setLegajoTx(int id_empleado, Legajo legajo, Connection conn) throws SQLException;
    Empleado buscarPorDni(String dni) throws SQLException;
}
