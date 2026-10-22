package services;

import dao.IDao;
import dao.MachineDao;
import entities.Machine;

import java.util.List;

/**
 * Couche service encapsulant la logique métier et déléguant les opérations au DAO Machine.
 */
public class MachineService implements IDao<Machine> {

    private final MachineDao dao;

    public MachineService() {
        this.dao = new MachineDao();
    }

    public MachineService(MachineDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(Machine o) {
        return dao.create(o);
    }

    @Override
    public boolean delete(Machine o) {
        return dao.delete(o);
    }

    @Override
    public boolean update(Machine o) {
        return dao.update(o);
    }

    @Override
    public Machine getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Machine> getAll() {
        return dao.getAll();
    }

    /**
     * Recherche des machines par marque (HQL).
     */
    public List<Machine> findByMarque(String marque) {
        return dao.findByMarque(marque);
    }
}
