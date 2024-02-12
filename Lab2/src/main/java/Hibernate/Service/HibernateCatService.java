package Hibernate.Service;

import Hibernate.DAO.HibernateCatDAO;
import Hibernate.HibernateCat;

import java.util.List;

public class HibernateCatService {
    private HibernateCatDAO catDAO = new HibernateCatDAO();
    public HibernateCat save(HibernateCat entity) {
        return catDAO.save(entity);
    }

    public void deleteById(long id) {
        catDAO.deleteById(id);
    }

    public void deleteByEntity(HibernateCat entity) {
        catDAO.deleteByEntity(entity);
    }

    public void deleteAll() {
        catDAO.deleteAll();
    }

    public HibernateCat update(HibernateCat entity) {
        return catDAO.update(entity);
    }

    public HibernateCat getById(long id) {
        return catDAO.getById(id);
    }

    public List<HibernateCat> getAll() {
        return catDAO.getAll();
    }

    public List<HibernateCat> getAllByOwnerId(long id) {
        return catDAO.getAllByOwnerId(id);
    }
}
