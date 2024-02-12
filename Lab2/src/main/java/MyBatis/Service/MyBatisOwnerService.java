package MyBatis.Service;

import MyBatis.DAO.MyBatisOwnerDAO;
import MyBatis.MyBatisOwner;

import java.util.List;

public class MyBatisOwnerService {
    private MyBatisOwnerDAO ownerDAO = new MyBatisOwnerDAO();
    public MyBatisOwner save(MyBatisOwner entity) {
        return ownerDAO.save(entity);
    }

    public void deleteById(long id) {
        ownerDAO.deleteById(id);
    }

    public void deleteByEntity(MyBatisOwner entity) {
        ownerDAO.deleteByEntity(entity);
    }

    public void deleteAll() {
        ownerDAO.deleteAll();
    }

    public MyBatisOwner update(MyBatisOwner entity) {
        return ownerDAO.update(entity);
    }

    public MyBatisOwner getById(long id) {
        return ownerDAO.getById(id);
    }

    public List<MyBatisOwner> getAll() {
        return ownerDAO.getAll();
    }
}
