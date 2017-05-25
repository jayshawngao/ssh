package jayshawn.ssh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jayshawn.ssh.entities.Department;

@Transactional
@Repository
public class DepartmentDao extends BaseDao{

	public List<Department> getAll(){
		String hql = "FROM Department";
		return getSession().createQuery(hql).list();
	}
}
