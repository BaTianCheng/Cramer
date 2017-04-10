package com.cw.cramer.auth.dao;

import com.cw.cramer.auth.entity.SysDepartment;
import com.cw.cramer.auth.entity.SysDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDepartmentDAO {
    long countByExample(SysDepartmentExample example);

    int deleteByExample(SysDepartmentExample example);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    List<SysDepartment> selectByExample(SysDepartmentExample example);

    int updateByExampleSelective(@Param("record") SysDepartment record, @Param("example") SysDepartmentExample example);

    int updateByExample(@Param("record") SysDepartment record, @Param("example") SysDepartmentExample example);
}