import Hibernate.HibernateOwner;
import Hibernate.Service.HibernateOwnerService;
import JDBC.JDBCOwner;
import JDBC.Service.JDBCOwnerService;
import MyBatis.MyBatisOwner;
import MyBatis.Service.MyBatisOwnerService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        jdbcTimer();
        hibernateTimer();
        myBatisTimer();
    }

    public static void jdbcTimer() throws SQLException {
        System.out.println("JDBC");
        JDBCOwnerService service = new JDBCOwnerService();
        service.deleteAll();

        long start = System.currentTimeMillis();
        for (int i = 1; i < 100; i++) {
            JDBCOwner owner = new JDBCOwner((long) i, "", LocalDate.of(1, 1, 1));
            service.save(owner);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("100 x save(): " + elapsed);

        start = System.currentTimeMillis();
        service.getAll();
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        System.out.println("getAll(): " + elapsed);

        service.deleteAll();
    }

    public static void hibernateTimer()
    {
        System.out.println("Hibernate");
        HibernateOwnerService service = new HibernateOwnerService();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            HibernateOwner owner = new HibernateOwner("", LocalDate.of(1, 1, 1));
            service.save(owner);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("100 x save(): " + elapsed);

        start = System.currentTimeMillis();
        service.getAll();
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        System.out.println("getAll(): " + elapsed);

        service.deleteAll();
    }

    public static void myBatisTimer()
    {
        System.out.println("MyBatis");
        MyBatisOwnerService service = new MyBatisOwnerService();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            MyBatisOwner owner = new MyBatisOwner((long) i, "", LocalDate.of(1, 1, 1));
            service.save(owner);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("100 x save(): " + elapsed);

        start = System.currentTimeMillis();
        service.getAll();
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        System.out.println("getAll(): " + elapsed);

        service.deleteAll();
    }
}
