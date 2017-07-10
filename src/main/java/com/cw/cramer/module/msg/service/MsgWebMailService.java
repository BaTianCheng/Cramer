package com.cw.cramer.module.msg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cw.cramer.common.base.BaseService;
import com.cw.cramer.common.constant.SequenceConstant;
import com.cw.cramer.common.constant.StatusConstant;
import com.cw.cramer.common.util.DateTimeUtils;
import com.cw.cramer.module.msg.dao.MsgWebMailDAO;
import com.cw.cramer.module.msg.dao.MsgWebMailStorageDAO;
import com.cw.cramer.module.msg.entity.MsgWebMail;
import com.cw.cramer.module.msg.entity.MsgWebMailStorage;
import com.cw.cramer.module.msg.entity.MsgWebMailStorageExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

/**
 * 站内信服务类
 * (目前站内信仅支持用户单向发送的简单功能)
 * @author wicks
 */
@Service(value = "msgWebMailService")
public class MsgWebMailService extends BaseService{
	
	@Autowired 
	private MsgWebMailDAO msgWebMailDAO;
	
	@Autowired 
	private MsgWebMailStorageDAO msgWebMailStorageDAO;
	
	/**
	 * 获取站内信
	 * @param id
	 * @return
	 */
	public MsgWebMailStorage getMsgWebMailStorage(int id){
		return convert(msgWebMailStorageDAO.selectByPrimaryKey(id));
	}
	
	/**
	 * 获取站内信列表
	 * @param pageNum
	 * @param pageSize
	 * @param title
	 * @param sortId
	 * @param sortType
	 * @return
	 */
	public PageInfo<MsgWebMailStorage> getMsgWebMailStorages(int pageNum, int pageSize, String title, String sortId, String sortType){
		PageHelper.startPage(pageNum, pageSize);
		MsgWebMailStorageExample example = new MsgWebMailStorageExample();
		String sortStr = "web_mail_storage.tag desc, web_mail.send_time desc";
		if(!Strings.isNullOrEmpty(title)){
			example.or().andMailTitleLike(title).andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		} else {
			example.or().andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		}
		if(!Strings.isNullOrEmpty(sortId)){
			sortStr = sortId+" "+sortType+", " + sortStr;
		}
		example.setOrderByClause(sortStr);
		List<MsgWebMailStorage> msgWebMailStorages = msgWebMailStorageDAO.selectByExample(example);
		msgWebMailStorages = convert(msgWebMailStorages);
		return new PageInfo<MsgWebMailStorage>(msgWebMailStorages);
	}
	
	/**
	 * 发送站内信
	 * @param mail
	 * @return
	 */
	public boolean sendWebMail(MsgWebMail mail){
		mail.setId(getNextSeq(SequenceConstant.SEQ_MSGWEBMAILID));
		mail.setStatus(StatusConstant.STATUS_ENABLED);
		if(mail.getSender() == null){
			mail.setSender(this.getCurrentUser().getId());
		}
		mail.setSendTime(DateTimeUtils.getCurrentTime());
		mail.setReceiverType(1);
		mail.setMethod(1);
		boolean isSuccess = msgWebMailDAO.insert(mail) >0 ? true : false;
		
		//发件存件
		MsgWebMailStorage senderStorage = new MsgWebMailStorage();
		senderStorage.setId(getNextSeq(SequenceConstant.SEQ_MSGWEBMAILSTORAGEID));
		senderStorage.setType(1);
		senderStorage.setStatus(StatusConstant.STATUS_ENABLED);
		senderStorage.setOwner(mail.getSender());
		senderStorage.setMailId(mail.getId());
		senderStorage.setMethod(mail.getMethod());
		msgWebMailStorageDAO.insert(senderStorage);
		
		//转换站内信为存储格式，向多用户发送
		if(!Strings.isNullOrEmpty(mail.getReceiver()) && mail.getReceiverType() == 1){
			List<Integer> userIds = JSON.parseArray(mail.getReceiver(), Integer.class);
			for(Integer userId : userIds){
				MsgWebMailStorage storage = new MsgWebMailStorage();
				storage.setId(getNextSeq(SequenceConstant.SEQ_MSGWEBMAILSTORAGEID));
				storage.setType(2);
				storage.setStatus(StatusConstant.STATUS_ENABLED);
				storage.setOwner(userId);
				storage.setMailId(mail.getId());
				storage.setMethod(1);
				msgWebMailStorageDAO.insert(storage);
			}
		}
		
		return isSuccess;
	}
	
	/**
	 * 转换
	 * @param msgWebMails
	 * @return
	 */
	public List<MsgWebMailStorage> convert (List<MsgWebMailStorage> msgWebMails){
		for(MsgWebMailStorage msgWebMail : msgWebMails){
			convert(msgWebMail);
		}
		return msgWebMails;
	}
	
	/**
	 * 转换
	 * @param msgWebMailStorage
	 * @return
	 */
	public MsgWebMailStorage convert (MsgWebMailStorage msgWebMailStorage){
		if(msgWebMailStorage.getMsgWebMail() != null){
			msgWebMailStorage.getMsgWebMail().setSenderName(getUserName(msgWebMailStorage.getMsgWebMail().getSender()));
		}
		return msgWebMailStorage;
	}
	
}
