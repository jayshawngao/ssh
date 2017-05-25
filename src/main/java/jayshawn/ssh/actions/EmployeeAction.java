package jayshawn.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import jayshawn.ssh.entities.Employee;
import jayshawn.ssh.services.DepartmentService;
import jayshawn.ssh.services.EmployeeService;

@Scope("prototype")
@Controller
public class EmployeeAction extends ActionSupport implements RequestAware,ModelDriven<Employee>,Preparable{

	
	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	
	private Integer id;
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Autowired
	private EmployeeService employeeService;	
	@Autowired
	private DepartmentService departmentService;
	
	public String list(){
		request.put("employees", employeeService.getAll());
		return "list";
	}
	
	public String delete(){
		employeeService.delete(id);
		return SUCCESS;
	}
	
	public String input(){
		request.put("departments", departmentService.getAll());
		return INPUT;
	}
	public void prepareInput(){
		if(id!=null){
			employee = employeeService.getEmpolyeeById(id);
		}
	}
	
	
	public String save(){
		if(id==null)
			employee.setCreateTime(new Date());
		employeeService.saveOrUpdate(employee);
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(id != null)
			employee = employeeService.getEmpolyeeById(id);
		else employee = new Employee();
	}
	private String lastName;
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public String validateLastName() throws Exception{
		if(employeeService.lastNameIsValid(lastName)){
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajax-success";
	}
	

	private Map<String, Object> request;
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void prepare() throws Exception {}

	@Override
	public Employee getModel() {
		return employee;
	}
	
	

}
