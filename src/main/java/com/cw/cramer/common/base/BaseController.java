package com.cw.cramer.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.common.base.entity.Result;
import com.cw.cramer.common.constant.ModuleType;
import com.cw.cramer.common.constant.OperateLogType;
import com.cw.cramer.common.constant.ResultConstant;
import com.cw.cramer.core.security.SecurityService;
import com.cw.cramer.sys.SysAPI;

/**
 * 基础控制器
 * @author wicks
 */
public class BaseController {
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	SysAPI sysAPI;
	
	/**
	 * 请求
	 */
	protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    /**
     * 初始化加载请求和响应
     * @param request
     * @param response
     */
    @ModelAttribute  
    public void init(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
    }
	
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
	public String renderJson(Object data){
		if(data instanceof Boolean){
			if((Boolean)data){
				return renderJson(ResultConstant.CODE_SUCCESS, ResultConstant.CODE_SUCCESS_DESC, data);
			} else {
				return renderJson(ResultConstant.CODE_INVOEERROR, ResultConstant.CODE_INVOEERROR_DESC, data);
			}
		} else {
			return renderJson(ResultConstant.CODE_SUCCESS, ResultConstant.CODE_SUCCESS_DESC, data);
		}
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
	 * 记录日志
	 * @param moduleType
	 * @param logType
	 * @param description
	 */
	public void record(ModuleType moduleType, OperateLogType logType, String description){
		sysAPI.record(moduleType, logType, description, request.getRequestURI(), null);
	}

}
