package com.cw.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisTest {

	 private static SqlSessionFactory sqlSessionFactory;
	    private static Reader reader; 

	    static{
	        try{
	            reader    = Resources.getResourceAsReader("mybatis-config.xml");
	            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }

	    public static SqlSessionFactory getSession(){
	        return sqlSessionFactory;
	    }
	    
	    public static void main(String[] args) {
	        SqlSession session = sqlSessionFactory.openSession();
	        try {
	        	List<CityModel> citys = session.selectList("com.cw.test.CityMapper.selectCitys");
	        	System.out.println(citys);
	        } finally {
	        session.close();
	        }
	    }

}
