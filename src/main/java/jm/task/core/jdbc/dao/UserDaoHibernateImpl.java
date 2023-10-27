package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users (" +
                "id bigint auto_increment primary key, " +
                "name varchar(30) not null," +
                "lastName varchar(30) not null, " +
                "age tinyint not null)").executeUpdate();
        Util.closeSession(session);
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
        Util.closeSession(session);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        System.out.println("User с именем '" + name + "' добавлен в базу данных");
        Util.closeSession(session);
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        User user = session.get(User.class, id);
        session.delete(user);
        Util.closeSession(session);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        List<User> users = session.createSQLQuery("SELECT * FROM Users").addEntity(User.class).list();
        Util.closeSession(session);
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        session.createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
        Util.closeSession(session);
    }

}
