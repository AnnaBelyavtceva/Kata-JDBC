package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    // создать таблицу юзеров
    void createUsersTable();

    // удалить таблицу юзеров
    void dropUsersTable();

    // сохранить юзера
    void saveUser(String name, String lastName, byte age) throws SQLException;

    // удалить юзера по id
    void removeUserById(long id);

    //вывести всех юзеров
    List<User> getAllUsers() throws SQLException;

    // очистить таблицу юзеров
    void cleanUsersTable() throws SQLException;
}
