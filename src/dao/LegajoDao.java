package dao;

import entities.Legajo;

import java.sql.SQLException;

public interface LegajoDao extends GenericDao<Legajo> {
    Legajo buscarPorNroLegajo(String nroLegajo) throws SQLException;
}
