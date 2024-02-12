package Hibernate.Service;

import Hibernate.DAO.HibernateOwnerDAO;
import Hibernate.HibernateOwner;

import java.util.List;

public class HibernateOwnerService {
    private HibernateOwnerDAO ownerDAO = new HibernateOwnerDAO();
    public HibernateOwner save(HibernateOwner entity) {
        return ownerDAO.save(entity);
    }

   public void deleteById(long id) {
        ownerDAO.deleteById(id);
    }

    public void deleteByEntity(HibernateOwner entity) {
        ownerDAO.deleteByEntity(entity);
    }

    public void deleteAll() {
        ownerDAO.deleteAll();
    }

    public HibernateOwner update(HibernateOwner entity) {
        return ownerDAO.update(entity);
    }

    public HibernateOwner getById(long id) {
        return ownerDAO.getById(id);
    }

    public List<HibernateOwner> getAll() {
        return ownerDAO.getAll();
    }
}
