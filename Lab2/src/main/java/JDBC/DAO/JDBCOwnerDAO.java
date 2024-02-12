package JDBC.DAO;

import JDBC.JDBCOwner;
import JDBC.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCOwnerDAO extends JDBCUtil {
    public JDBCOwner save(JDBCOwner entity) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO owner (id, name, birthDate) " + "VALUES(?, ?, ?)";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setObject(3, entity.getBirthDate());

        preparedStatement.executeUpdate();

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }

        return entity;
    }


    public void deleteById(long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM owner WHERE id=?";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, id);

        preparedStatement.executeUpdate();

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }


    public void deleteByEntity(JDBCOwner entity) throws SQLException {
        deleteById(entity.getId());
    }


    public void deleteAll() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM owner";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.executeUpdate();

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }


    public JDBCOwner update(JDBCOwner entity) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE owner SET name = ?, birthDate = ? WHERE id = ?";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, entity.getName());
        preparedStatement.setObject(2, entity.getBirthDate());
        preparedStatement.setLong(3, entity.getId());

        preparedStatement.executeUpdate();
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }

        return entity;
    }

    public JDBCOwner getById(long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM owner WHERE id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        JDBCOwner owner = new JDBCOwner();
        owner.setId(resultSet.getLong(1));
        owner.setName(resultSet.getString(2));
        owner.setBirthDate(resultSet.getObject(3, LocalDate.class));

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }


        return owner;
    }

    public List<JDBCOwner> getAll() throws SQLException {
        Connection connection = getConnection();
        List<JDBCOwner> owners = new ArrayList<>();

        String sql = "SELECT id, name, birthDate FROM owner";

        Statement statement = null;
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            JDBCOwner owner = new JDBCOwner();
            owner.setId(resultSet.getLong("id"));
            owner.setName(resultSet.getString("name"));
            owner.setBirthDate(resultSet.getObject("birthDate", LocalDate.class));

            owners.add(owner);
        }

        if (statement != null) {
                statement.close();
        }
        if (connection != null) {
            connection.close();
        }

        return owners;
    }

}
