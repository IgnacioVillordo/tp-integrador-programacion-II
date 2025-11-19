package services;

import entities.Legajo;

public interface LegajoService extends GenericService<Legajo> {
    Legajo buscarPorIdEmpleado(int id_empleado) throws Exception;
}
