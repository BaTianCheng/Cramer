package com.cw.cramer.auth.dao;

import com.cw.cramer.auth.entity.SysDepartmentRole;
import com.cw.cramer.auth.entity.SysDepartmentRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDepartmentRoleDAO {
    long countByExample(SysDepartmentRoleExample example);

    int deleteByExample(SysDepartmentRoleExample example);

    int insert(SysDepartmentRole record);

    int insertSelective(SysDepartmentRole record);

    List<SysDepartmentRole> selectByExample(SysDepartmentRoleExample example);

    int updateByExampleSelective(@Param("record") SysDepartmentRole record, @Param("example") SysDepartmentRoleExample example);

    int updateByExample(@Param("record") SysDepartmentRole record, @Param("example") SysDepartmentRoleExample example);
}