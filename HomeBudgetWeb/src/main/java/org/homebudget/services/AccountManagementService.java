package org.homebudget.services;

import javax.annotation.Resource;
import org.homebudget.dao.AccountRepository;
import org.homebudget.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountManagementService {

    @Resource
    private AccountRepository accountRepository;
    
    public void saveAccount(Account account){
        accountRepository.save(account);
    }
}