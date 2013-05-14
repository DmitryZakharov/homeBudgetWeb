package org.homebudget.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.model.UserRole.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public List<UserDetails> getAllUsers() {

        Query q = getSessionFactory().openSession().createQuery(
                "from USER_DETAILS");

        return (List<UserDetails>) q.list();

    }

    public UserDetails getUser(String userNickname) {

        Query q = getSessionFactory().openSession().createQuery(
                "from USER_DETAILS where USER_USERNAME=?");
        q.setString(0, userNickname);
        List<UserDetails> users = (List<UserDetails>) q.list();

        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }
    
    public UserRole getRole(Authority authority){
         Query q = getSessionFactory().openSession().createQuery(
                "from USER_ROLES where AUTHORITY=?");
        q.setString(0, authority.name());
        List<UserRole> userRoles = (List<UserRole>) q.list();

        if (userRoles.size() == 1) {
            return userRoles.get(0);
        }
        return null;
    }

    public Long getUserId(String username) {

        Query q = getSessionFactory().openSession().createQuery(
                "from USER_DETAILS where USER_USEERNAME=?");
        q.setString(0, username);

        List<UserDetails> users = (List<UserDetails>) q.list();
        UserDetails user = null;

        if (users.size() == 1) {
            user = users.get(0);
        } else {
            return null;
        }

        return user.getUserId();
    }

    public void addUser(UserDetails user, Authority authority) {
        UserRole uRole = new UserRole();
        uRole.setAuthority(authority);
        user.addUserRole(uRole);
        getSessionFactory().openSession().save(uRole);
        getSessionFactory().openSession().save(user);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
