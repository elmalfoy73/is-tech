package MyBatis.Service;

import MyBatis.DAO.MyBatisCatDAO;
import MyBatis.MyBatisCat;

import java.util.List;

public class MyBatisCatService {
    private MyBatisCatDAO catDAO = new MyBatisCatDAO();
    public MyBatisCat save(MyBatisCat entity){
        return catDAO.save(entity);
    }
    public void deleteById(long id){
        catDAO.deleteById(id);
    }
    public void deleteByEntity(MyBatisCat entity){
        catDAO.deleteByEntity(entity);
    }
    public void deleteAll(){
        catDAO.deleteAll();
    }
    public MyBatisCat update(MyBatisCat entity){
        return catDAO.update(entity);
    }
    public MyBatisCat getById(long id){
        return catDAO.getById(id);
    }
    public List<MyBatisCat> getAll(){
        return catDAO.getAll();
    }

    public List<MyBatisCat> getAllByOwnerId(long id){
        return catDAO.getAllByOwnerId(id);
    }
}
