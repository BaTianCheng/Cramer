package com.cw.cramer.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.common.constant.ModuleType;
import com.cw.cramer.common.constant.OperateLogType;
import com.cw.cramer.sys.service.SysOperateLogService;
import com.cw.cramer.sys.service.SysSequenceService;

/**
 * 系统API
 * @author wicks
 */
@Service(value = "sysAPI")
public class SysAPI {
	
	@Autowired
	SysSequenceService sysSequenceService;
	
	@Autowired
	SysOperateLogService sysOperateLogService;

	/**
	 * 获取下一个序列
	 * @param seqName
	 * @return
	 */
	public int getNextSeq(String seqName){
		return sysSequenceService.getNextSeq(seqName);
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
		sysOperateLogService.record(moduleType, logType, description, url, remarks);
	}
	
}
