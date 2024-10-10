package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory = new Util().getSessionFactory();
    private static Session session = null;
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    " (Id INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(40) NOT NULL, Lastname VARCHAR(60)" +
                    " NOT NULL, Age INT NOT NULL);").executeUpdate();

            session.getTransaction().commit();

    } catch (Exception e) {
            System.out.println(" Exception в методе createUsersTable " + e);
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e + " Exception методе dropUsersTable " + e);

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = new Util().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println(e + " Exception в методе saveUser " + e);
        }
}
    @Override
    public void removeUserById(long id) {
        try {
            session = new Util().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createQuery("DELETE User WHERE Id = " + id).executeUpdate();
            session.getTransaction().commit();

        }catch (Exception e) {
            System.out.println(e + "Exception в методе removeUserById " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try {
            session = new Util().getSessionFactory().getCurrentSession();
            session.beginTransaction();

            listUsers = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();

            System.out.println(listUsers.toString());

        } catch (Exception e) {
            System.out.println(" Exception в методе getAllUsers " + e);
        }
        return listUsers;
}
    @Override
    public void cleanUsersTable() {
        try {
        session = new Util().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE User").executeUpdate();
        session.getTransaction().commit();

        }catch (Exception e) {
            System.out.println(" Exception в методе cleanUsersTable " + e);
        }
    }
}
