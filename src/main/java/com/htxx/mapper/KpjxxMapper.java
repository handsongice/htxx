package com.htxx.mapper;

import com.htxx.entity.Kpjxx;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface KpjxxMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Kpjxx record);

    int insertSelective(Kpjxx record);

    Kpjxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Kpjxx record);

    int updateByPrimaryKey(Kpjxx record);

    List<Kpjxx> selectByParams(Map<String, Object> map);

    List<Kpjxx> selectByKpjId(List<Long> kpjIds);

    List<Kpjxx> selectKpjDeptByParams(Map<String, Object> map);


    @Select("SELECT" +
            "   k.* , " +
            "   d.id as deptId, " +
            "   d.`name` as deptName " +
            "FROM" +
            "   dl_department_employee de, " +
            "   dl_department_kpj dk, " +
            "   dl_department d, " +
            "   dl_kpjxx k " +
            "WHERE" +
            "   dk.dept_id = de.dept_id " +
            "   AND dk.dept_id = d.id" +
            "   AND dk.kpj_id = k.id " +
            "   AND de.employee_id = #{employeeId,jdbcType=BIGINT} " +
            "   AND k.enterprise_id = #{enterpriseId,jdbcType=BIGINT}")
    List<Kpjxx> selectEmpDeptKpjxxList(@Param("employeeId") Long employeeId, @Param("enterpriseId") Long enterpriseId);
}