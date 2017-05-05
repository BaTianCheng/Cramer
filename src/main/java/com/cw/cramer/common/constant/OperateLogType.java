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
	Query(220001),Export(220002),
	Insert(230001),Update(230002),Delete(230003),Import(230004),
	Login_In(291001),Login_Out(291002);
	
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
			case 291001 : return "登录";
			case 291002 : return "登出";
			case 220001 : return "查询";
			case 220002 : return "导出";
			case 230001 : return "添加";
			case 230002 : return "修改";
			case 230003 : return "删除";
			case 230004 : return "导入";
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
