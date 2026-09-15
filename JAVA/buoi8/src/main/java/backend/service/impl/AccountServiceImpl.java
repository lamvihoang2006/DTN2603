package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.service.IAccountService;
import entity.Account;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private IAccountRepository accountRepository = new AccountRepositoryImpl();

    @Override
    public List<Account> getAllAccounts() throws Exception {
        return accountRepository.getAllAccounts();
    }

    @Override
    public boolean addAccount(String email, String username, String fullName, int departmentId, int positionId) throws Exception {
        return accountRepository.addAccount(email, username, fullName, departmentId, positionId);
    }

    @Override
    public boolean updateUsername(int id, String newUsername) throws Exception {
        return accountRepository.updateUsername(id, newUsername);
    }

    @Override
    public boolean deleteAccount(int id) throws Exception {
        return accountRepository.deleteAccount(id);
    }
}