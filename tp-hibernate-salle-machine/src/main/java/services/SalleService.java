package services;

import dao.IDao;
import dao.SalleDao;
import entities.Salle;

import java.util.List;

/**
 * Couche service encapsulant la logique métier et déléguant les opérations au DAO Salle.
 */
public class SalleService implements IDao<Salle> {

    private final IDao<Salle> dao;

    public SalleService() {
        this.dao = new SalleDao();
    }

    public SalleService(IDao<Salle> dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(Salle o) {
        return dao.create(o);
    }

    @Override
    public boolean delete(Salle o) {
        return dao.delete(o);
    }

    @Override
    public boolean update(Salle o) {
        return dao.update(o);
    }

    @Override
    public Salle getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Salle> getAll() {
        return dao.getAll();
    }
}
