package com.cw.cramer.sys.dao;

import com.cw.cramer.sys.entity.SysOperateLog;
import com.cw.cramer.sys.entity.SysOperateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOperateLogDAO {
    long countByExample(SysOperateLogExample example);

    int deleteByExample(SysOperateLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysOperateLog record);

    int insertSelective(SysOperateLog record);

    List<SysOperateLog> selectByExample(SysOperateLogExample example);

    SysOperateLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysOperateLog record, @Param("example") SysOperateLogExample example);

    int updateByExample(@Param("record") SysOperateLog record, @Param("example") SysOperateLogExample example);

    int updateByPrimaryKeySelective(SysOperateLog record);

    int updateByPrimaryKey(SysOperateLog record);
}