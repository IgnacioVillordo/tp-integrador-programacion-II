package dao;

import entities.Legajo;

import java.sql.Connection;
import java.sql.SQLException;

public interface EmpleadoLegajoDao {
    void setLegajo(String dni, Legajo legajo) throws SQLException;
    void setLegajoTx(String dni, Legajo legajo, Connection conn) throws SQLException;
}
