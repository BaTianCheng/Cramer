package com.cw.cramer.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.AuthAPI;
import com.cw.cramer.common.constant.CommonConstant;
import com.cw.cramer.common.constant.ModuleType;
import com.cw.cramer.common.constant.OperateLogType;
import com.cw.cramer.common.constant.SequenceConstant;
import com.cw.cramer.core.security.SecurityService;
import com.cw.cramer.sys.dao.SysOperateLogDAO;
import com.cw.cramer.sys.entity.SysOperateLog;
import com.cw.cramer.sys.entity.SysOperateLogExample;
import com.cw.cramer.sys.entity.SysOperateLogExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

import cn.hutool.core.date.DateUtil;

/**
 * 操作日志服务类
 * @author wicks
 */
@Service(value="sysOperateLogService")
public class SysOperateLogService {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SysOperateLogDAO sysOperateLogDAO;
	
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@Autowired
	private AuthAPI authAPI;
	
	/**
	 * 日志记录
	 * @param sysOperateLog
	 */
	public void insert(SysOperateLog sysOperateLog){
		sysOperateLog.setId(sysSequenceService.getNextSeq(SequenceConstant.SEQ_SYSOPERATELOGID));
		sysOperateLogDAO.insert(sysOperateLog);
	}
	
	/**
	 * 日志记录
	 * @param moduleType
	 * @param logType
	 * @param description
	 * @param url
	 * @param remarks
	 */
	public void record(ModuleType moduleType, OperateLogType logType, String description, String url, String remarks){
		SysOperateLog sysOperateLog = new SysOperateLog();
		sysOperateLog.setOperateType(logType.getValue());
		sysOperateLog.setContent(description);
		sysOperateLog.setModuleId(moduleType.getValue());
		sysOperateLog.setUrl(url);
		sysOperateLog.setRemarks(remarks);
		sysOperateLog.setOperateIp(securityService.getCurrentSession().getHost());
		sysOperateLog.setOperateBy(securityService.getCurrentUser().getId());
		sysOperateLog.setOperateTime(DateUtil.date());
		CommonConstant.logThread.insertQueue(sysOperateLog);
	}
	
	/**
	 * 日志记录
	 * @param moduleType
	 * @param logType
	 * @param description
	 */
	public void record(ModuleType moduleType, OperateLogType logType, String description){
		record(moduleType, logType, description, null, null);
	}
	
	/**
	 * 获取日志列表
	 * @param pageNum
	 * @param pageSize
	 * @param sysOperateLog
	 * @return
	 */
	public PageInfo<SysOperateLog> getSysOperateLogs(int pageNum, int pageSize, SysOperateLog sysOperateLog) {
		PageHelper.startPage(pageNum, pageSize);
		SysOperateLogExample example = new SysOperateLogExample();
		Criteria criteria = example.createCriteria();
		if(sysOperateLog.getModuleId() != null){
			criteria.andModuleIdEqualTo(sysOperateLog.getModuleId());
		}
		if(sysOperateLog.getOperateType() != null){
			criteria.andOperateTypeEqualTo(sysOperateLog.getOperateType());
		}
		if(!Strings.isNullOrEmpty(sysOperateLog.getContent())){
			criteria.andContentLike(sysOperateLog.getContent());
		}
		example.or(criteria);
		example.setOrderByClause("operate_time desc");
		List<SysOperateLog> list = sysOperateLogDAO.selectByExample(example);
		
		//描述修改
		for(SysOperateLog log : list){
			log.setOperator(authAPI.getUserName(log.getOperateBy()));
			log.setModuleDesc(ModuleType.getDesc(log.getModuleId()));
			log.setOperateTypeDesc(OperateLogType.getDesc(log.getOperateType()));
		}
		
		return new PageInfo<SysOperateLog>(list);
	}


}
