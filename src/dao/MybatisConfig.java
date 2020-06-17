package dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		String resource = "mybatis-config.xml";		//Mybatis 환경설정 파일 읽기. src 경로에 저장.
		//읽어와서 무언가를 하겠다.

		try {
			Reader reader = Resources.getResourceAsReader(resource);//문자를 읽어옴
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}

