package com.cw.cramer.auth.dao;

import com.cw.cramer.auth.entity.SysRoleAuthority;
import com.cw.cramer.auth.entity.SysRoleAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleAuthorityDAO {
    long countByExample(SysRoleAuthorityExample example);

    int deleteByExample(SysRoleAuthorityExample example);

    int insert(SysRoleAuthority record);

    int insertSelective(SysRoleAuthority record);

    List<SysRoleAuthority> selectByExample(SysRoleAuthorityExample example);

    int updateByExampleSelective(@Param("record") SysRoleAuthority record, @Param("example") SysRoleAuthorityExample example);

    int updateByExample(@Param("record") SysRoleAuthority record, @Param("example") SysRoleAuthorityExample example);
}