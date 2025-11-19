package services;

import dao.LegajoDao;
import entities.Empleado;
import entities.Legajo;

import java.time.LocalDate;

public class LegajoServiceImpl implements LegajoService {
    private final LegajoDao legajoDao;

    public LegajoServiceImpl(LegajoDao legajoDao) {
        this.legajoDao = legajoDao;
    }

    @Override
    public void save(Legajo entity) throws Exception {
        this.validateEntity(entity);
        this.legajoDao.save(entity);
    }

    @Override
    public void update(Legajo entity) throws Exception {
        this.validateEntity(entity);
        this.legajoDao.update(entity);
    }

    @Override
    public void delete(int id) throws Exception {
        this.validateIntegerId(id);
        this.legajoDao.delete(id);
    }

    @Override
    public void saveTx(Legajo entity) throws Exception {

    }

    @Override
    public void updateTx(Legajo entity) throws Exception {

    }

    @Override
    public void delteTx(Legajo entity) throws Exception {

    }

    @Override
    public Legajo getById(int id) throws Exception {
        this.validateIntegerId(id);
        return this.legajoDao.getById(id);
    }

    @Override
    public Legajo buscarPorNroLegajo(String nroLegajo) throws Exception {
        this.validateStringNotEmpty(nroLegajo);
        return this.legajoDao.buscarPorNroLegajo(nroLegajo);
    }

    private void validateEntity(Legajo entity) throws Exception {
        if (entity.getId() <= 0)
            throw new IllegalArgumentException("Id invalido");
        if (entity.getNroLegajo() == null || entity.getNroLegajo().isEmpty() || entity.getNroLegajo().length() > 20)
            throw new IllegalArgumentException("Numero de legajo invalido. No puede estar vacio ni tener mas de 20 caracteres");
        if (entity.getCategoria() == null || entity.getCategoria().isEmpty() || entity.getCategoria().length() > 30)
            throw new IllegalArgumentException("La categoria es invalida. No puede estar vacia ni tener mas de 30 caracteres");
        if (entity.getEstado() == null)
            throw new IllegalArgumentException("Estado invalido");
        if (entity.getFechaAlta() == null || entity.getFechaAlta().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha no puede ser futura ni nula");
        if (entity.getObservaciones().length() > 255)
            throw new IllegalArgumentException("Las observaciones no pueden tener mas de 255 caracteres");
    }

    private void validateIntegerId(int id) throws Exception {
        if (id <= 0)
            throw new IllegalArgumentException("El id no debe ser un numero negativo");
    }

    private void validateStringNotEmpty(String value) throws Exception {
        if (value.isEmpty())
            throw new IllegalArgumentException("No puede ser una cadena vacia");
    }
}
