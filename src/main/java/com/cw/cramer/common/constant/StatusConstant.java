package com.cw.cramer.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 状态常量
 * @author wicks
 */
public class StatusConstant {

	/**
	 * 删除状态
	 */
	public static int STATUS_DELETED = -1;
	
	/**
	 * 不可用状态
	 */
	public static int STATUS_UNENABLED = 0;
	
	/**
	 * 可用状态
	 */
	public static int STATUS_ENABLED = 1;
	
	/**
	 * 锁定状态
	 */
	public static int STATUS_LOCKED = 2;
	
	/**
	 * 可用及锁定状态
	 */
	public static List<Integer> STATUS_ENABLED_LOCKED = Arrays.asList(1, 2);
	
}
