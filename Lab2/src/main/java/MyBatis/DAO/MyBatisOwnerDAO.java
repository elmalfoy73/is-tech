package MyBatis.DAO;

import MyBatis.DAO.Mapper.OwnerMapper;
import MyBatis.MyBatisOwner;
import MyBatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisOwnerDAO {
    public MyBatisOwner save(MyBatisOwner entity) {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.save(entity);
        session.close();
        return entity;
    }

    public void deleteById(long id) {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.deleteById(id);
        session.close();
    }

    public void deleteByEntity(MyBatisOwner entity) {
        deleteById(entity.getId());
    }

    public void deleteAll() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.deleteAll();
        session.close();
    }

    public MyBatisOwner update(MyBatisOwner entity) {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.update(entity);
        session.close();
        return entity;
    }

    public MyBatisOwner getById(long id) {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        MyBatisOwner owner = ownerMapper.getById(id);
        session.close();
        return owner;
    }

    public List<MyBatisOwner> getAll() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.bildSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        List<MyBatisOwner> owners = ownerMapper.getAll();
        session.close();
        return owners;
    }
}
