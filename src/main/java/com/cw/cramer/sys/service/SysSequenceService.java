package com.cw.cramer.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.sys.dao.SysSequenceDAO;

/**
 * 系统序列服务类
 * @author wicks
 */
@Service(value="sysSequenceService")
public class SysSequenceService {
	
	@Autowired
	SysSequenceDAO sysSequenceDAO;
	
	/**
	 * 获取下一个序列
	 * @param seqName
	 * @return
	 */
	public int getNextSeq(String seqName){
		return sysSequenceDAO.selectNext(seqName);
	}

}
