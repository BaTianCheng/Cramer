package com.cw.cramer.auth.dao;

import com.cw.cramer.auth.entity.SysAuthority;
import com.cw.cramer.auth.entity.SysAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAuthorityDAO {
    long countByExample(SysAuthorityExample example);

    int deleteByExample(SysAuthorityExample example);

    int insert(SysAuthority record);

    int insertSelective(SysAuthority record);

    List<SysAuthority> selectByExample(SysAuthorityExample example);

    int updateByExampleSelective(@Param("record") SysAuthority record, @Param("example") SysAuthorityExample example);

    int updateByExample(@Param("record") SysAuthority record, @Param("example") SysAuthorityExample example);
}