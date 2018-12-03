package com.htxx.mapper;

import com.htxx.entity.Department;
import com.htxx.entity.DepartmentEmployee;
import com.htxx.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentEmployeeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DepartmentEmployee record);

    int insertSelective(DepartmentEmployee record);

    DepartmentEmployee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartmentEmployee record);

    int updateByPrimaryKey(DepartmentEmployee record);

    DepartmentEmployee findByParams(DepartmentEmployee record);

    int deleteByParams(DepartmentEmployee record);

    @Select("    select dept_id\n" +
            "    from dl_department_employee as a, dl_employee as b \n" +
            "    where a.employee_id=b.id and    a.employee_id = #{id,jdbcType=BIGINT}" +
            "  and b.enterprise_id = #{enterPriseId,jdbcType=BIGINT}")
    List<Long> selectByEmployeeId(@Param("id") Long id, @Param("enterPriseId") Long enterPriseId);

    Employee selectEmpDeptsList(Employee employee);

    Department selectDeptEmpsList(Department department);
}