package com.cw.cramer.common.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作日志类型枚举类
 * @author wicks
 */
public enum ModuleType {
	/**
	 * 权限
	 */
	Auth(101001),
	/**
	 * 消息
	 */
	Msg(102001),
	/**
	 * 总线
	 */
	Esb(103001),
	/**
	 * 系统
	 */
	Sys(109001);
	
	private int code;
	
	private ModuleType(int code) {
		this.code = code;
	}
	
	/**
	 * 获取描述
	 * @param code
	 * @return
	 */
	public static String getDesc(int code){
		switch (code) {
			case 101001 : return "权限管理";
			case 102001 : return "消息管理";
			case 103001 : return "总线服务";
			case 109001 : return "系统管理";
			default : return String.valueOf ( code );
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
		for(ModuleType type : this.values()){
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
