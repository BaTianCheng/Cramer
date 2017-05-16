package com.cw.cramer.module.msg.dao;

import com.cw.cramer.module.msg.entity.MsgWebMailStorage;
import com.cw.cramer.module.msg.entity.MsgWebMailStorageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgWebMailStorageDAO {
    long countByExample(MsgWebMailStorageExample example);

    int deleteByExample(MsgWebMailStorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MsgWebMailStorage record);

    int insertSelective(MsgWebMailStorage record);

    List<MsgWebMailStorage> selectByExample(MsgWebMailStorageExample example);

    MsgWebMailStorage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MsgWebMailStorage record, @Param("example") MsgWebMailStorageExample example);

    int updateByExample(@Param("record") MsgWebMailStorage record, @Param("example") MsgWebMailStorageExample example);

    int updateByPrimaryKeySelective(MsgWebMailStorage record);

    int updateByPrimaryKey(MsgWebMailStorage record);
}