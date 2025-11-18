package dao;

import entities.Legajo;

import java.sql.Connection;
import java.sql.SQLException;

public interface EmpleadoLegajoDao {
    void setLegajo(int id_empleado, Legajo legajo) throws SQLException;
    void setLegajoTx(int id_empleado, Legajo legajo, Connection conn) throws SQLException;
}
