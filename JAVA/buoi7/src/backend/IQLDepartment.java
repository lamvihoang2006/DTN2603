package backend;

public interface IQLDepartment {
    void showAllDepartments();
    void addDepartment(String departmentName);
    void deleteDepartment(int id);
    void updateDepartmentName(int id, String newName);
}