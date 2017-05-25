package jayshawn.ssh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jayshawn.ssh.dao.EmployeeDao;
import jayshawn.ssh.entities.Employee;

@Transactional
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	public List<Employee> getAll(){
		return employeeDao.getAll();
	}
	
	public void delete(Integer id){
		employeeDao.delete(id);
	}
	
	public void saveOrUpdate(Employee employee){
		employeeDao.saveOrUpdate(employee);
	}
	
	public boolean lastNameIsValid(String lastName){
		return employeeDao.getByLastName(lastName)==null;
		
	}
	
	public Employee getEmpolyeeById(Integer id){
		return employeeDao.getEmpolyeeById(id);
	}
}
