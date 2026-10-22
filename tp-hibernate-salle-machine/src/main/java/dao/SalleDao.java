package dao;

import entities.Salle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

/**
 * Implémentation concrète des opérations CRUD pour l'entité Salle via Hibernate.
 */
public class SalleDao implements IDao<Salle> {

    @Override
    public boolean create(Salle o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erreur create(Salle) : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Salle o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erreur delete(Salle) : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Salle o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erreur update(Salle) : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Salle getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Salle.class, id);
        } catch (Exception e) {
            System.err.println("Erreur getById(Salle) : " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Salle> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Salle", Salle.class).list();
        } catch (Exception e) {
            System.err.println("Erreur getAll(Salle) : " + e.getMessage());
            return List.of();
        }
    }
}
