package com.cw.cramer.common.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作日志类型枚举类
 * @author wicks
 */
public enum OperateLogType {
	Login_In(101),Login_Out(102),Query(201),Export(202),Insert(301),Update(302),Delete(303),Import(304);
	
	private int code;
	
	private OperateLogType(int code) {
		this.code = code;
	}
	
	/**
	 * 获取描述
	 * @param code
	 * @return
	 */
	public String getDesc(int code){
		switch (code) {
			case 101 : return "登录";
			case 102 : return "登出";
			case 201 : return "查询";
			case 202 : return "导出";
			case 301 : return "添加";
			case 302 : return "修改";
			case 303 : return "删除";
			case 304 : return "导入";
			default : return String.valueOf ( this.code );
		}
	}
	
	/**
	 * 获取枚举值
	 * @return
	 */
	public int getValue(){
		return code;
	}
	
	/**
	 * 获取全部值
	 * @return
	 */
	@SuppressWarnings("static-access")
	public List<Map<String, Object>> getValues(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map;
		for(OperateLogType type : this.values()){
			map = new HashMap<>();
			map.put("value", type.getValue());
			map.put("desc", getDesc(type.getValue()));
			list.add(map);
		}
		return list;
	}
	
    @Override
    public String toString() {
    	return String.valueOf ( this.code );
    }

}
