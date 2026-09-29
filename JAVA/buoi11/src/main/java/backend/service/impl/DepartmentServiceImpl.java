package backend.service.impl;

import backend.repository.IDepartmentRepository;
import backend.repository.impl.DepartmentRepositoryImpl;
import backend.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {
    private IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();

    @Override
    public boolean addDepartment(String departmentName) throws Exception {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new Exception("Tên phòng ban không được để trống!");
        }

        if (departmentRepository.isDepartmentNameExists(departmentName)) {
            throw new Exception("Tên phòng ban đã tồn tại!");
        }

        return departmentRepository.addDepartment(departmentName);
    }

    @Override
    public String importCsv(String filePath) throws Exception {

        return utils.CsvHelper.importCsvGeneric(filePath, 1, data -> {
            String departmentName = data[0].trim();
            this.addDepartment(departmentName);
        });
    }
}