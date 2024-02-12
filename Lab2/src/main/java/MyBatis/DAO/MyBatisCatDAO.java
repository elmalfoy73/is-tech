package MyBatis.DAO;

import MyBatis.DAO.Mapper.CatMapper;
import MyBatis.MyBatisCat;
import MyBatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisCatDAO {
    public MyBatisCat save(MyBatisCat entity){
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.save(entity);
        session.close();
        return entity;
    }
    public void deleteById(long id){
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.deleteById(id);
        session.close();
    }
    public void deleteByEntity(MyBatisCat entity){
        deleteById(entity.getId());
    }
    public void deleteAll(){
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.deleteAll();
        session.close();
    }
    public MyBatisCat update(MyBatisCat entity){
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.update(entity);
        session.close();
        return entity;
    }
    public MyBatisCat getById(long id){
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        MyBatisCat cat = catMapper.getById(id);
        session.close();
        return cat;
    }
    public List<MyBatisCat> getAll(){
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        List<MyBatisCat> cats = catMapper.getAll();
        session.close();
        return cats;
    }

    public List<MyBatisCat> getAllByOwnerId(long id){
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        List<MyBatisCat> cats = catMapper.getAllByOwnerId(id);
        session.close();
        return cats;
    }
}
