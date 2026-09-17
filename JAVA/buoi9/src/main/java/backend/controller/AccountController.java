package backend.controller;

import backend.service.IAccountService;
import backend.service.impl.AccountServiceImpl;
import entity.Account;

import java.util.List;

public class AccountController {
    private IAccountService accountService = new AccountServiceImpl();

    public List<Account> getAllAccounts() throws Exception {
        return accountService.getAllAccounts();
    }

    public boolean addAccount(String email, String username, String fullName, int departmentId, int positionId) throws Exception {
        return accountService.addAccount(email, username, fullName, departmentId, positionId);
    }

    public boolean updateUsername(int id, String newUsername) throws Exception {
        return accountService.updateUsername(id, newUsername);
    }

    public boolean deleteAccount(int id) throws Exception {
        return accountService.deleteAccount(id);
    }
}