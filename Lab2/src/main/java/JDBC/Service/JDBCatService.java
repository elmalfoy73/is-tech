package JDBC.Service;

import JDBC.DAO.JDBCatDAO;
import JDBC.JDBCat;

import java.sql.SQLException;
import java.util.List;

public class JDBCatService {
    private JDBCatDAO catDAO = new JDBCatDAO();
    public JDBCat save(JDBCat entity) throws SQLException {
        return catDAO.save(entity);
    }

    public void deleteById(long id) throws SQLException {
        catDAO.deleteById(id);
    }

    public void deleteByEntity(JDBCat entity) throws SQLException {
        catDAO.deleteByEntity(entity);
    }

    public void deleteAll() throws SQLException {
        catDAO.deleteAll();
    }

    public JDBCat update(JDBCat entity) throws SQLException {
        return catDAO.update(entity);
    }

    public JDBCat getById(long id) throws SQLException {
        return catDAO.getById(id);
    }

    public List<JDBCat> getAll() throws SQLException {
        return catDAO.getAll();
    }

    public List<JDBCat> getAllByOwnerId(long id) throws SQLException {
        return catDAO.getAllByOwnerId(id);
    }
}
