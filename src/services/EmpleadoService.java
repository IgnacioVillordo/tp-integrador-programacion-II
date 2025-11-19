package services;

import entities.Empleado;

public interface EmpleadoService extends GenericService<Empleado> {
    Empleado buscarPorDni(String dni) throws Exception;
}
