package Hibernate.DAO;

import Hibernate.HibernateOwner;
import Hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateOwnerDAO {
    public HibernateOwner save(HibernateOwner entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
        return entity;
    }

    public void deleteById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        HibernateOwner owner = session.get(HibernateOwner.class, id);
        session.remove(owner);
        tx1.commit();
        session.close();
    }

    public void deleteByEntity(HibernateOwner entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(entity);
        tx1.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from HibernateOwner").executeUpdate();;
        tx1.commit();
        session.close();
    }

    public HibernateOwner update(HibernateOwner entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
        return entity;
    }

    public HibernateOwner getById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(HibernateOwner.class, id);
    }
    public List<HibernateOwner> getAll(){
        List<HibernateOwner> owners = (List<HibernateOwner>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From HibernateOwner").list();
        return owners;
    }
}
