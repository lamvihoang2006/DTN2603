package backend.repository;

public interface IDepartmentRepository {
    boolean isDepartmentNameExists(String departmentName) throws Exception;
    boolean addDepartment(String departmentName) throws Exception;
}