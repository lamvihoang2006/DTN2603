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
        if (username == null || username.length() <= 6 || username.length() >= 100) {
            throw new Exception("Username phải lớn hơn 6 và nhỏ hơn 100 ký tự!");
        }
        if (accountRepository.isUsernameExists(username)) {
            throw new Exception("Username đã tồn tại (Unique)!");
        }

        if (email == null || email.length() <= 6 || email.length() >= 100) {
            throw new Exception("Email phải lớn hơn 6 và nhỏ hơn 100 ký tự!");
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        if (!email.matches(emailRegex)) {
            throw new Exception("Email không đúng định dạng Gmail (vd: a@gmail.com)!");
        }
        if (accountRepository.isEmailExists(email)) {
            throw new Exception("Email đã tồn tại (Unique)!");
        }

        if (fullName == null || fullName.length() <= 6 || fullName.length() >= 100) {
            throw new Exception("Fullname phải lớn hơn 6 và nhỏ hơn 100 ký tự!");
        }

        if (!accountRepository.isDepartmentExists(departmentId)) {
            throw new Exception("Department ID không tồn tại trong hệ thống!");
        }
        if (!accountRepository.isPositionExists(positionId)) {
            throw new Exception("Position ID không tồn tại trong hệ thống!");
        }

        return accountRepository.addAccount(email, username, fullName, departmentId, positionId);
    }

    @Override
    public boolean updateUsername(int id, String newUsername) throws Exception {
        if (!accountRepository.isAccountExists(id)) {
            throw new Exception("Account ID không tồn tại!");
        }

        if (newUsername == null || newUsername.length() <= 6 || newUsername.length() >= 100) {
            throw new Exception("Username mới phải lớn hơn 6 và nhỏ hơn 100 ký tự!");
        }
        if (accountRepository.isUsernameExists(newUsername)) {
            throw new Exception("Username mới đã tồn tại, vui lòng chọn tên khác!");
        }

        return accountRepository.updateUsername(id, newUsername);
    }

    @Override
    public boolean deleteAccount(int id) throws Exception {
        if (!accountRepository.isAccountExists(id)) {
            throw new Exception("Không thể xóa! Account ID không tồn tại.");
        }

        return accountRepository.deleteAccount(id);
    }

    @Override
    public String importCsv(String filePath) throws Exception {
        java.io.File inputFile = new java.io.File(filePath);
        if (!inputFile.exists()) {
            throw new Exception("File không tồn tại! Vui lòng kiểm tra lại đường dẫn.");
        }

        String errorFilePath = inputFile.getParent() + "/error_" + inputFile.getName();
        int successCount = 0;
        int errorCount = 0;

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(inputFile));
             java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(errorFilePath))) {

            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    bw.write(line + ",Error_Message\n");
                    isFirstLine = false;
                    continue;
                }

                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length < 5) {
                    bw.write(line + ",Thiếu cột dữ liệu\n");
                    errorCount++;
                    continue;
                }

                try {
                    String email = data[0].trim();
                    String username = data[1].trim();
                    String fullName = data[2].trim();
                    int depId = Integer.parseInt(data[3].trim());
                    int posId = Integer.parseInt(data[4].trim());

                    this.addAccount(email, username, fullName, depId, posId);
                    successCount++;
                } catch (Exception e) {
                    bw.write(line + "," + e.getMessage() + "\n");
                    errorCount++;
                }
            }
        }

        if (errorCount == 0) {
            new java.io.File(errorFilePath).delete();
            return "Import thành công " + successCount + " bản ghi. Không có lỗi.";
        } else {
            return "Import thành công " + successCount + " bản ghi. Có " + errorCount + " bản ghi lỗi.\n-> Đã xuất file lỗi tại: " + errorFilePath;
        }
    }
}