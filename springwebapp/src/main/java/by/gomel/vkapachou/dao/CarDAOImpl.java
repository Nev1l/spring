package by.gomel.vkapachou.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import by.gomel.vkapachou.bean.User;

/**
 * Created by Администратор on 15.04.2017.
 */

@Repository
public class CarDAOImpl implements CarDAO {
    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations mongoOperation;

    public void saveUser(User user) {
        mongoOperation.save(user);
        //after save use will get ID!
    }

    public User getUser(User user) {
        //Query searchUserQuery = new Query(Criteria.where("name").is("Angelina Jolie"));
        Query searchUserQuery = new Query(Criteria.where("token").is("testToken12345"));
        User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        return savedUser;
    }

    public User updateUser(User user) {
        //mongoOperation.updateFirst(searchUserQuery, Update.update("email", "angelina_jolie@gmail.com"), User.class).;
        //User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);
        return null;
    }

    public void removeUser(User user) {
        Query searchUserQuery = new Query(Criteria.where("token").is("testToken12345"));
        mongoOperation.remove(searchUserQuery, User.class);
    }

}
