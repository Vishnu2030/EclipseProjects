
package daostuff;
import java.util.List;

//POJO interface 
public interface DepartmentDAO {
	void addDepartment(Department dRef);		
	Department findDepartment(int dno);			
	List<Department> findDepartments();			
	void modifyDepartment(Department dRef);		
	void removeDepartment(Department dRef);  
}
