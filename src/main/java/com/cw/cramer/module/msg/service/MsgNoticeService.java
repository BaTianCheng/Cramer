package com.cw.cramer.module.msg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.common.base.BaseService;
import com.cw.cramer.common.constant.SequenceConstant;
import com.cw.cramer.common.constant.StatusConstant;
import com.cw.cramer.common.util.DateTimeUtils;
import com.cw.cramer.module.msg.dao.MsgNoticeDAO;
import com.cw.cramer.module.msg.entity.MsgNotice;
import com.cw.cramer.module.msg.entity.MsgNoticeExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

/**
 * 通知公告服务类
 * @author wicks
 */
@Service(value = "msgNoticeService")
public class MsgNoticeService extends BaseService{
	
	@Autowired 
	private MsgNoticeDAO msgNoticeDAO;
	
	/**
	 * 获取通知公告
	 * @param id
	 * @return
	 */
	public MsgNotice getMsgNotice(int id){
		return convert(msgNoticeDAO.selectByPrimaryKey(id));
	}
	
	/**
	 * 获取通知公告列表
	 * @param pageNum
	 * @param pageSize
	 * @param title
	 * @param sortId
	 * @param sortType
	 * @return
	 */
	public PageInfo<MsgNotice> getMsgNotices(int pageNum, int pageSize, String title, String sortId, String sortType){
		PageHelper.startPage(pageNum, pageSize);
		MsgNoticeExample example = new MsgNoticeExample();
		String sortStr = "notice.top_flag desc, notice.sort asc, notice.create_time desc, notice.id asc";
		if(!Strings.isNullOrEmpty(title)){
			example.or().andTitleLike(title).andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		} else {
			example.or().andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		}
		if(!Strings.isNullOrEmpty(sortId)){
			sortStr = sortId+" "+sortType+", " + sortStr;
		}
		example.setOrderByClause(sortStr);
		List<MsgNotice> msgNotices = msgNoticeDAO.selectByExample(example);
		msgNotices = convert(msgNotices);
		return new PageInfo<MsgNotice>(msgNotices);
	}
	
	/**
	 * 添加公告
	 * @param notice
	 * @return
	 */
	public boolean insert(MsgNotice notice){
		notice.setId(getNextSeq(SequenceConstant.SEQ_SYSROLEID));
		return msgNoticeDAO.insert(notice)>0 ? true : false;
	}
	
	/**
	 * 修改公告
	 * @param notice
	 * @return
	 */
	public boolean update(MsgNotice notice){
		notice.setUpdateBy(this.getCurrentUser().getId());
		notice.setUpdateTime(DateTimeUtils.getCurrentTime());
		return msgNoticeDAO.updateByPrimaryKey(notice)>0 ? true : false;
	}
	
	/**
	 * 更新公告基本信息
	 * @param editedUser
	 * @return
	 */
	public boolean updateInfo(MsgNotice editedMsgNotice){
		MsgNotice notice = getMsgNotice(editedMsgNotice.getId());
		notice.setTitle(editedMsgNotice.getTitle());
		notice.setContent(editedMsgNotice.getContent());
		notice.setStatus(editedMsgNotice.getStatus());
		notice.setRemarks(editedMsgNotice.getRemarks());
		return update(notice);
	}
	
	/**
	 * 删除公告(更新标志位)
	 * @param id
	 * @return
	 */
	public boolean delete(int id){
		MsgNotice notice = getMsgNotice(id);
		if(notice != null){
			notice.setStatus(StatusConstant.STATUS_DELETED);
			return msgNoticeDAO.updateByPrimaryKey(notice)>0 ? true : false;
		} else {
			return false;
		}
	}
	
	/**
	 * 转换
	 * @param msgNotice
	 * @return
	 */
	public MsgNotice convert (MsgNotice msgNotice){
		if(msgNotice != null){
			msgNotice.setCreateName(getUserName(msgNotice.getCreateBy()));
			msgNotice.setUpdateName(getUserName(msgNotice.getUpdateBy()));
		}
		return msgNotice;
	}
	
	/**
	 * 转换
	 * @param msgNotices
	 * @return
	 */
	public List<MsgNotice> convert (List<MsgNotice> msgNotices){
		for(MsgNotice notice : msgNotices){
			convert(notice);
		}
		return msgNotices;
	}

}
