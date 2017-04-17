package com.cw.cramer.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.sys.service.SysSequenceService;

/**
 * 系统API
 * @author wicks
 */
@Service(value = "sysAPI")
public class SysAPI {
	
	@Autowired
	SysSequenceService sysSequenceService;

	/**
	 * 获取下一个序列
	 * @param seqName
	 * @return
	 */
	public int getNextSeq(String seqName){
		return sysSequenceService.getNextSeq(seqName);
	}
	
}
