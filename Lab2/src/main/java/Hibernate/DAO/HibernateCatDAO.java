package Hibernate.DAO;

import Hibernate.HibernateCat;
import Hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateCatDAO {

    public HibernateCat save(HibernateCat entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    public void deleteById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        HibernateCat cat = session.get(HibernateCat.class, id);
        session.remove(cat);
        tx1.commit();
        session.close();
    }

    public void deleteByEntity(HibernateCat entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(entity);
        tx1.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from HibernateCat").executeUpdate();;
        tx1.commit();
        session.close();    }

    public HibernateCat update(HibernateCat entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(entity);
        tx1.commit();
        session.close();
        return entity;
    }

    public HibernateCat getById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(HibernateCat.class, id);
    }

    public List<HibernateCat> getAll() {
        List<HibernateCat> cats = (List<HibernateCat>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From HibernateCat").list();
        return cats;
    }

    public List<HibernateCat> getAllByOwnerId(long id) {
        List<HibernateCat> cats = (List<HibernateCat>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From HibernateCat where owner.id = :ownerId").setParameter("ownerId", id).list();
        return cats;
    }
}
