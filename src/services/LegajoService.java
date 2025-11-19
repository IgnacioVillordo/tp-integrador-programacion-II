package services;

import entities.Legajo;

public interface LegajoService extends GenericService<Legajo> {
    Legajo buscarPorNroLegajo(String nroLegajo) throws Exception;
}
