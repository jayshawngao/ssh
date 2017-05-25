package jayshawn.ssh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jayshawn.ssh.dao.DepartmentDao;
import jayshawn.ssh.entities.Department;

@Service
@Transactional
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
}
