package com.cw.cramer.module.msg.dao;

import com.cw.cramer.module.msg.entity.MsgWebMail;
import com.cw.cramer.module.msg.entity.MsgWebMailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgWebMailDAO {
    long countByExample(MsgWebMailExample example);

    int deleteByExample(MsgWebMailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MsgWebMail record);

    int insertSelective(MsgWebMail record);

    List<MsgWebMail> selectByExampleWithBLOBs(MsgWebMailExample example);

    List<MsgWebMail> selectByExample(MsgWebMailExample example);

    MsgWebMail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MsgWebMail record, @Param("example") MsgWebMailExample example);

    int updateByExampleWithBLOBs(@Param("record") MsgWebMail record, @Param("example") MsgWebMailExample example);

    int updateByExample(@Param("record") MsgWebMail record, @Param("example") MsgWebMailExample example);

    int updateByPrimaryKeySelective(MsgWebMail record);

    int updateByPrimaryKeyWithBLOBs(MsgWebMail record);

    int updateByPrimaryKey(MsgWebMail record);
}