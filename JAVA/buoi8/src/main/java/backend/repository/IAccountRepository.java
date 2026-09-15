package backend.repository;

import entity.Account;
import java.util.List;

public interface IAccountRepository {
    List<Account> getAllAccounts() throws Exception;
    boolean addAccount(String email, String username, String fullName, int departmentId, int positionId) throws Exception;
    boolean updateUsername(int id, String newUsername) throws Exception;
    boolean deleteAccount(int id) throws Exception;
}