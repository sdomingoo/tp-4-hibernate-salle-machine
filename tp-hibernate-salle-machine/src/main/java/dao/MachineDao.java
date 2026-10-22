package dao;

import entities.Machine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 * Implémentation concrète des opérations CRUD et requêtes HQL pour l'entité Machine.
 */
public class MachineDao implements IDao<Machine> {

    @Override
    public boolean create(Machine o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erreur create(Machine) : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Machine o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erreur delete(Machine) : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Machine o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erreur update(Machine) : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Machine getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Machine.class, id);
        } catch (Exception e) {
            System.err.println("Erreur getById(Machine) : " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Machine> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Machine", Machine.class).list();
        } catch (Exception e) {
            System.err.println("Erreur getAll(Machine) : " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Requête HQL personnalisée pour rechercher les machines d'une marque donnée.
     * @param marque Nom de la marque
     * @return Liste des machines correspondantes
     */
    public List<Machine> findByMarque(String marque) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Machine> query = session.createQuery("from Machine m where m.marque = :marque", Machine.class);
            query.setParameter("marque", marque);
            return query.list();
        } catch (Exception e) {
            System.err.println("Erreur findByMarque(Machine) : " + e.getMessage());
            return List.of();
        }
    }
}
