package services;

import entities.Empleado;
import entities.Legajo;

import java.util.List;

public interface EmpleadoService extends GenericService<Empleado> {
    Empleado buscarPorDni(String dni) throws Exception;
    List<Empleado> getAll() throws Exception;
    void setLegajo(int id_empleado, Legajo legajo) throws Exception;
    Legajo getLegajo(int id_empleado) throws Exception;
}
