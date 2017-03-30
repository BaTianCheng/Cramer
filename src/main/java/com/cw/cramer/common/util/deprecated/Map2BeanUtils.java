package com.cw.cramer.common.util.deprecated;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Map2BeanUtils<T> {
	public T convert(final Map<String, Object> paramMap, Class<T> cls, boolean ignoreCase) {

		T obj = null;
		try {
			obj = cls.newInstance();

			// 20161122忽略下划线,全部大写
			HashMap<String, Object> map = new HashMap<String, Object>();
			for(Map.Entry<String, Object> entry : paramMap.entrySet()) {  
				map.put(entry.getKey().toUpperCase().replaceAll("_", ""), entry.getValue());
			}

			BeanInfo beanInfo = Introspector.getBeanInfo(cls);

			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				// 忽略大小写要全部大写 //20161122忽略下划线
				String beanKey = ignoreCase ? property.getName().toUpperCase().replaceAll("_", "")
						: property.getName().replaceAll("_", "");

				// 对于BigDecimal的单独处理
				if (property.getPropertyType().toString().equals("class java.math.BigDecimal")) {
					Object value = map.get(beanKey);
					if (value == null)
						value = 0;
					BigDecimal big_value = new BigDecimal(value.toString());
					Method setter = property.getWriteMethod();
					setter.invoke(obj, big_value);

					// 对于Integer的单独处理
				} else if (property.getPropertyType().toString().equals("class java.lang.Integer")) {
					Object value = map.get(beanKey);
					if (value == null)
						value = 0;
					Integer i_value = new Integer(value.toString());
					Method setter = property.getWriteMethod();
					setter.invoke(obj, i_value);

					// 对于Short的单独处理
				} else if (property.getPropertyType().toString().equals("class java.lang.Short")) {
					Object value = map.get(beanKey);
					if (value == null)
						value = 0;
					Short s_value = new Short(value.toString());
					Method setter = property.getWriteMethod();
					setter.invoke(obj, s_value);
				} else {
					Object value = null;
					if (map.containsKey(beanKey)) {
						value = map.get(beanKey);
						// 得到property对应的setter方法
						Method setter = property.getWriteMethod();
						setter.invoke(obj, value);

					}
				}

			}

		} catch (Exception e) {
		}

		return obj;

	}
}
