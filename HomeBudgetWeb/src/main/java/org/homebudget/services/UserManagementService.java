package org.homebudget.services;

import javax.annotation.Resource;
import org.homebudget.dao.UserRepository;
import org.homebudget.dao.UserRoleRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.model.UserRole.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagementService {

    @Resource
    private UserRepository userRepositoryDaoImpl;
    
    @Resource
    private UserRoleRepository userRoleRepository;

    @Transactional
    public void saveUserDetails(UserDetails userDetails) {
        userRepositoryDaoImpl.save(userDetails);
    }
    
    @Transactional
    public void saveUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
     
//    public UserRole getRole(Role role){
//        UserRole result = userRoleRepository.findByUserRole(role);
//        return  result;
//     }
}