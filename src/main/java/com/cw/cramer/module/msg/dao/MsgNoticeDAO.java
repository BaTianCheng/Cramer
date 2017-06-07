package com.cw.cramer.module.msg.dao;

import com.cw.cramer.module.msg.entity.MsgNotice;
import com.cw.cramer.module.msg.entity.MsgNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgNoticeDAO {
    long countByExample(MsgNoticeExample example);

    int deleteByExample(MsgNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MsgNotice record);

    int insertSelective(MsgNotice record);

    List<MsgNotice> selectByExampleWithBLOBs(MsgNoticeExample example);

    List<MsgNotice> selectByExample(MsgNoticeExample example);

    MsgNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MsgNotice record, @Param("example") MsgNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") MsgNotice record, @Param("example") MsgNoticeExample example);

    int updateByExample(@Param("record") MsgNotice record, @Param("example") MsgNoticeExample example);

    int updateByPrimaryKeySelective(MsgNotice record);

    int updateByPrimaryKeyWithBLOBs(MsgNotice record);

    int updateByPrimaryKey(MsgNotice record);
    
    int selectNextSortId();
}