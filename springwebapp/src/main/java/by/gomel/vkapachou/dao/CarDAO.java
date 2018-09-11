package by.gomel.vkapachou.dao;

import by.gomel.vkapachou.bean.User;

/**
 * Created by Администратор on 15.04.2017.
 */
public interface CarDAO {
    void saveUser(User user);
    User getUser(User user);
    User updateUser(User user);
    void removeUser(User user);
}
