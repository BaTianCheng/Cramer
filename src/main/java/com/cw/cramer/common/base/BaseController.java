package com.cw.cramer.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.common.base.entity.Result;
import com.cw.cramer.common.constant.ResultConstant;
import com.cw.cramer.common.util.LogUtils;
import com.cw.cramer.core.security.SecurityService;

/**
 * 基础控制器
 * @author wicks
 */
public class BaseController {
	
	@Autowired
	SecurityService securityService;
	
	/**
	 * 返回结果(JSON格式)
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public String renderJson(String code, String message, Object data){
		Result result = new Result();
		result.setResultCode(code);
		result.setMessage(message);
		result.setData(data);
		return JSON.toJSONString(result);
	}
	
	/**
	 * 返回成功结果(JSON格式)
	 * @param data
	 * @return
	 */
	public String renderSuccessJson(Object data){
		return renderJson(ResultConstant.CODE_SUCCESS, ResultConstant.CODE_SUCCESS_DESC, data);
	}
	
	/**
	 * 返回失败结果(JSON格式)
	 * @param data
	 * @return
	 */
	public String renderFailJson(Object data){
		return renderJson(ResultConstant.CODE_FAIL, ResultConstant.CODE_FAIL_DESC, data);
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public SysUser getCurrentUser(){
		return securityService.getCurrentUser();
	}
	
	/**
	 * 记录日志（暂时记录在log中）
	 * @param info
	 */
	public void log(String info){
		LogUtils.info(getCurrentUser().getName() + "：" + info);
	}

}
