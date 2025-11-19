package services;

import entities.Empleado;

import java.util.List;

public interface EmpleadoService extends GenericService<Empleado> {
    Empleado buscarPorDni(String dni) throws Exception;
    List<Empleado> getAll() throws Exception;
}
