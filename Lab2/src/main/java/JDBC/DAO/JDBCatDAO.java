package JDBC.DAO;

import JDBC.JDBCUtil;
import JDBC.JDBCat;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCatDAO extends JDBCUtil {
    public JDBCat save(JDBCat entity) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO cat (id, name, birthDate, breed, colour, ownerId) VALUES(?, ?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setObject(3, entity.getBirthDate());
        preparedStatement.setString(4, entity.getBreed());
        preparedStatement.setString(5, entity.getColour());
        preparedStatement.setLong(6, entity.getOwnerId());

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

       String sql = "DELETE FROM cat WHERE id=?";

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

    public void deleteByEntity(JDBCat entity) throws SQLException {
        deleteById(entity.getId());
    }

    public void deleteAll() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM cat";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.executeUpdate();

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public JDBCat update(JDBCat entity) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE cat SET name = ?, birthDate = ?, breed = ?, colour = ? WHERE id = ?";

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

    public JDBCat getById(long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "SELECT id, name, birthDate, breed, colour, ownerId FROM cat WHERE id = ?";

        JDBCat cat = new JDBCat();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        cat.setId(resultSet.getLong("id"));
        cat.setName(resultSet.getString("name"));
        cat.setBirthDate(resultSet.getObject("birthDate", LocalDate.class));
        cat.setBreed(resultSet.getString("breed"));
        cat.setColour(resultSet.getString("colour"));
        cat.setOwnerId(resultSet.getLong("ownerId"));

        preparedStatement.executeUpdate();


        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }

        return cat;
    }

    public List<JDBCat> getAll() throws SQLException {
        Connection connection = getConnection();
        List<JDBCat> cats = new ArrayList<>();

        String sql = "SELECT id, name, birthDate, breed, colour, ownerId FROM cat";

        Statement statement = null;

        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            JDBCat cat = new JDBCat();
            cat.setId(resultSet.getLong("id"));
            cat.setName(resultSet.getString("name"));
            cat.setBirthDate(resultSet.getObject("birthDate", LocalDate.class));
            cat.setBreed(resultSet.getString("breed"));
            cat.setColour(resultSet.getString("colour"));
            cat.setOwnerId(resultSet.getLong("ownerId"));

            cats.add(cat);
        }

        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }

        return cats;
    }

    public List<JDBCat> getAllByOwnerId(long id) throws SQLException {
        Connection connection = getConnection();
        List<JDBCat> cats = new ArrayList<>();

        String sql = "SELECT id, name, birthDate, breed, colour, ownerId FROM cat WHERE ownerid = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            JDBCat cat = new JDBCat();
            cat.setId(resultSet.getLong("id"));
            cat.setName(resultSet.getString("name"));
            cat.setBirthDate(resultSet.getObject("birthDate", LocalDate.class));
            cat.setBreed(resultSet.getString("breed"));
            cat.setColour(resultSet.getString("colour"));
            cat.setOwnerId(resultSet.getLong("ownerId"));

            cats.add(cat);
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }

        return cats;
    }
}
