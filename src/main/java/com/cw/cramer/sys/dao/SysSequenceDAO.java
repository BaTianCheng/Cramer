package com.cw.cramer.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cw.cramer.sys.entity.SysSequence;
import com.cw.cramer.sys.entity.SysSequenceExample;

public interface SysSequenceDAO {
    long countByExample(SysSequenceExample example);

    int deleteByExample(SysSequenceExample example);

    int deleteByPrimaryKey(String seqName);

    int insert(SysSequence record);

    int insertSelective(SysSequence record);

    List<SysSequence> selectByExample(SysSequenceExample example);

    SysSequence selectByPrimaryKey(String seqName);

    int updateByExampleSelective(@Param("record") SysSequence record, @Param("example") SysSequenceExample example);

    int updateByExample(@Param("record") SysSequence record, @Param("example") SysSequenceExample example);

    int updateByPrimaryKeySelective(SysSequence record);

    int updateByPrimaryKey(SysSequence record);
    
    int selectNext(String seqName);
}