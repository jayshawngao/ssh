package jayshawn.ssh.dao;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jayshawn.ssh.entities.Employee;

@Repository
@Transactional
public class EmployeeDao extends BaseDao{
	
	
	public List<Employee> getAll(){
		//����ʹ��������������, ������list.jspҳ������Ҫ������Ϣʱ, ���Żᱻ�ӳټ��ص��´���
		//����Ҳ��<many-to-one>�йر��ӳټ���
		String hql = "FROM Employee e LEFT JOIN FETCH e.department";
		return getSession().createQuery(hql).list();
	}
	
	public void delete(Integer id){
		String hql = "DELETE FROM Employee e WHERE e.id = ?";
		getSession().createQuery(hql).setParameter(0, id).executeUpdate();
	}
	
	public void saveOrUpdate(Employee employee){
		getSession().saveOrUpdate(employee);
	}

	public Employee getByLastName(String lastName){
		String hql = "FROM Employee e WHERE e.lastName = ?";
		org.hibernate.Query query = getSession().createQuery(hql).setString(0, lastName);
		return (Employee) query.uniqueResult();
	}
	
	public Employee getEmpolyeeById(Integer id){
		String hql = "FROM Employee e LEFT JOIN FETCH e.department WHERE e.id = ?";
		org.hibernate.Query query = getSession().createQuery(hql).setParameter(0, id);
		return (Employee) query.uniqueResult();
		
	}
}
