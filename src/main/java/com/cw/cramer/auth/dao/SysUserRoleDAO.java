package com.cw.cramer.auth.dao;

import com.cw.cramer.auth.entity.SysUserRole;
import com.cw.cramer.auth.entity.SysUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDAO {
    long countByExample(SysUserRoleExample example);

    int deleteByExample(SysUserRoleExample example);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);
}