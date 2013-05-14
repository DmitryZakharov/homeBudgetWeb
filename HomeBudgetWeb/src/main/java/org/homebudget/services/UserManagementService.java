package org.homebudget.services;

import org.homebudget.dao.UserRepositoryDaoImpl;
import org.homebudget.model.UserRole;
import org.homebudget.model.UserRole.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

    @Autowired
    @Qualifier("userRepositoryDao")
    private UserRepositoryDaoImpl userRepositoryDaoImpl;
    
    public UserRole getRole(Authority authority){
        return userRepositoryDaoImpl.getRole(authority);
    }
}