package backend.service;

public interface IDepartmentService {
    boolean addDepartment(String departmentName) throws Exception;
    String importCsv(String filePath) throws Exception;
}