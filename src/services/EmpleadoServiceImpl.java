package services;

import config.TransaccionManager;
import dao.EmpleadoDao;
import dao.LegajoDao;
import entities.Empleado;
import entities.Legajo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmpleadoServiceImpl implements EmpleadoService {
    private final EmpleadoDao empleadoDao;
    private final LegajoDao legajoDao;
    private final TransaccionManager transaccionManager;

    public EmpleadoServiceImpl(EmpleadoDao empleadoDao, LegajoDao legajoDao, TransaccionManager transaccionManager) {
        this.empleadoDao = empleadoDao;
        this.legajoDao = legajoDao;
        this.transaccionManager = transaccionManager;
    }

    @Override
    public void save(Empleado entity) throws Exception {
        this.validateEntity(entity);
        this.empleadoDao.save(entity);
    }

    @Override
    public void update(Empleado entity) throws Exception {
        this.validateEntity(entity);
        this.empleadoDao.update(entity);
    }

    @Override
    public void delete(int id) throws Exception {
        this.validateIntegerId(id);
        this.empleadoDao.delete(id);
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
        this.validateIntegerId(id);
        return this.empleadoDao.getById(id);
    }

    @Override
    public List<Empleado> getAll() throws Exception {
        return this.empleadoDao.getAll();
    }

    @Override
    public void setLegajo(int id_empleado, Legajo legajo) throws Exception {
        try {
            this.transaccionManager.begin();
            Optional<Integer> id_legajo = this.legajoDao.save(legajo);
            if (!id_legajo.isPresent()) {
                throw new Exception("No se pudo guardar el Legajo numero: " + legajo.getNroLegajo());
            }
            this.empleadoDao.setLegajo(id_empleado, id_legajo.get());
            this.transaccionManager.commit();
        } catch (Exception e) {
            this.transaccionManager.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Empleado buscarPorDni(String dni) throws Exception {
        return this.empleadoDao.buscarPorDni(dni);
    }

    private void validateEntity(Empleado entity) throws Exception {
        if (entity.getId() < 0)
            throw new IllegalArgumentException("El id no puede ser un nùmero negativo");
        if (entity.getDni().trim().isEmpty() || entity.getDni() == null || entity.getDni().length() > 16)
            throw new IllegalArgumentException("El dni no puede estar vacìo");
        if (entity.getNombre().trim().isEmpty() || entity.getNombre() == null || entity.getNombre().length() > 80)
            throw new IllegalArgumentException("El nombre no puede estar vacìo");
        if (entity.getApellido().trim().isEmpty() || entity.getApellido() == null || entity.getNombre().length() > 80)
            throw new IllegalArgumentException("El apellido no puede estar vacìo");
        if (entity.getEmail().trim().isEmpty() || !entity.getEmail().contains("@") || !entity.getEmail().contains(".") || entity.getEmail().length() > 120)
            throw new IllegalArgumentException("El email no puede estar vacìo, o debe contener @");
        if (entity.getFechaIngreso() == null || entity.getFechaIngreso().isAfter(LocalDate.now()) || entity.getFechaIngreso().equals(""))
            throw new IllegalArgumentException("La fecha de ingreso no puede estar vacia o ser anterior a la fecha actual");
        if (entity.getArea() == null || entity.getArea().isEmpty() || entity.getArea().length() > 50)
            throw new IllegalArgumentException("La longitud del texto del area no debe ser mayor a 50 caracteres");
    }

    private void validateIntegerId(int id) throws Exception {
        if (id <= 0)
            throw new IllegalArgumentException("El id no debe ser un numero negativo");
    }
}
