package untitled7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import untitled7.model.User;

import java.util.List;

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    MongoTemplate mongoTemplate;


    public List getUsers() {
        return mongoTemplate.findAll(User.class);
    }
}