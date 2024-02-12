package MyBatis;

import MyBatis.DAO.Mapper.CatMapper;
import MyBatis.DAO.Mapper.OwnerMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class MyBatisUtil {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Cats";
    private static final String DB_USERNAME = "elizabeth";
    private static final String DB_PASSWORD = "";
    public static SqlSessionFactory bildSqlSessionFactory(){
        DataSource dataSource = new PooledDataSource(DB_DRIVER, DB_URL, DB_USERNAME, DB_USERNAME) ;
        Environment environment = new Environment( "Development", new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(CatMapper.class);
        configuration.addMapper(OwnerMapper.class);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(configuration);
        return factory;
    }
}
