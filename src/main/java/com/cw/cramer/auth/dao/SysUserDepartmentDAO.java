package com.cw.cramer.auth.dao;

import com.cw.cramer.auth.entity.SysUserDepartment;
import com.cw.cramer.auth.entity.SysUserDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserDepartmentDAO {
    long countByExample(SysUserDepartmentExample example);

    int deleteByExample(SysUserDepartmentExample example);

    int insert(SysUserDepartment record);

    int insertSelective(SysUserDepartment record);

    List<SysUserDepartment> selectByExample(SysUserDepartmentExample example);

    int updateByExampleSelective(@Param("record") SysUserDepartment record, @Param("example") SysUserDepartmentExample example);

    int updateByExample(@Param("record") SysUserDepartment record, @Param("example") SysUserDepartmentExample example);
}