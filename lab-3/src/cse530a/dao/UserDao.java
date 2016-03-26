package cse530a.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import cse530a.model.User;

public class UserDao {
    public static User createUser(Session session, String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        session.save(user);
        return user;
    }
    
    public static void updateUser(Session session, User user) {
        session.save(user);
    }
    
    public static void deleteUser(Session session, User user) {
        session.delete(user);
    }

    public static User retrieveUser(Session session, Long id) {
        return (User) session.get(User.class, id);
    }
    
    public static User retrieveUser(Session session, String username) {
        Query query = session.createQuery("from User user where user.username = :name");
        query.setString("name", username);
        return (User) query.uniqueResult();
    }
}
