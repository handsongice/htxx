package com.htxx.mapper;

import com.htxx.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKeyWithBLOBs(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> selectByParams(Map<String, Object> map);

    Employee findByParams(Employee record);

    int delEmployee(Long id);

    @Select("SELECT DISTINCT " +
            " d.id " +
            "FROM" +
            " dl_department_employee de, " +
            " dl_department d, " +
            " dl_employee e " +
            "WHERE" +
            " d.id = de.dept_id " +
            " AND e.id = de.employee_id " +
            " and e.enterprise_id = d.enterprise_id" +
            " and d.enterprise_id = #{enterpriseId}" +
            " and e.id = #{empId}")
    List<Long> selectDeptsByEmp(@Param("empId") Long empId, @Param("enterpriseId") Long enterpriseId);

    @Select("SELECT DISTINCT\n" +
            "\tr.id \n" +
            "FROM\n" +
            "\tdl_role_employee re,\n" +
            "\tdl_role r,\n" +
            "\tdl_employee e \n" +
            "WHERE\n" +
            "\tre.role_id = r.id \n" +
            "\tAND re.employee_id = e.id \n" +
            "\tAND r.enterprise_id = e.enterprise_id \n" +
            "\tAND r.enterprise_id = #{enterpriseId} \n" +
            "\tAND e.id = #{empId}\n" +
            "\t")
    List<Long> selectRolesByEmp(@Param("empId") Long empId, @Param("enterpriseId") Long enterpriseId);
}