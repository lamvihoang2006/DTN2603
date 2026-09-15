package backend;

public interface IQLAccount {
    void showAllAccounts();
    void addAccount(String email, String username, String fullName, int departmentId, int positionId);
    void deleteAccount(int id);
    void updateUsername(int id, String newUsername);
}