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
	Auth(100001);
	
	private int code;
	
	private ModuleType(int code) {
		this.code = code;
	}
	
	/**
	 * 获取描述
	 * @param code
	 * @return
	 */
	public String getDesc(int code){
		switch (code) {
			case 1000001 : return "权限管理";
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
