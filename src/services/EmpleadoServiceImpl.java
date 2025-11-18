package services;

import dao.GenericDao;
import entities.Empleado;

import java.util.List;

public class EmpleadoServiceImpl implements GenericService<Empleado>{
    private final GenericDao<Empleado> empleadoDao;

    public EmpleadoServiceImpl(GenericDao<Empleado> empleadoDao) {
        this.empleadoDao = empleadoDao;
    }

    @Override
    public void save(Empleado entity) throws Exception {

    }

    @Override
    public void update(Empleado entity) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public void saveTx(Empleado entity) throws Exception {

    }

    @Override
    public void updateTx(Empleado entity) throws Exception {

    }

    @Override
    public void delteTx(Empleado entity) throws Exception {

    }

    @Override
    public Empleado getById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Empleado> getAll() throws Exception {
        return List.of();
    }
}
