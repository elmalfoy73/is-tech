package JDBC.Service;

import JDBC.DAO.JDBCOwnerDAO;
import JDBC.DAO.JDBCatDAO;
import JDBC.JDBCOwner;
import JDBC.JDBCat;

import java.sql.SQLException;
import java.util.List;

public class JDBCOwnerService {
    private JDBCOwnerDAO ownerDAO = new JDBCOwnerDAO();
    public JDBCOwner save(JDBCOwner entity) throws SQLException {
        return ownerDAO.save(entity);
    }

    public void deleteById(long id) throws SQLException {
        ownerDAO.deleteById(id);
    }

    public void deleteByEntity(JDBCOwner entity) throws SQLException {
        ownerDAO.deleteByEntity(entity);
    }

    public void deleteAll() throws SQLException {
        ownerDAO.deleteAll();
    }

    public JDBCOwner update(JDBCOwner entity) throws SQLException {
        return ownerDAO.update(entity);
    }

    public JDBCOwner getById(long id) throws SQLException {
        return ownerDAO.getById(id);
    }

    public List<JDBCOwner> getAll() throws SQLException {
        return ownerDAO.getAll();
    }
}
