package org.zerock.myapp.config;

import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamResource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Configuration("root-context.xml")
public class RootContextXml
	implements InitializingBean, DisposableBean {


	
	//============= 1. HikariCP Data Source Configuration =============//
	
	@Primary
//	@Bean
	@Bean(name="hikariDataSource", destroyMethod="close")
	public HikariDataSource hikariDataSource() {
		log.debug("hikariDataSource() invoked.");
		
		HikariConfig config = new HikariConfig();
		
//		config.setDriverClassName("oracle.jdbc.OracleDriver");
//		config.setJdbcUrl("jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		
		config.setUsername("ADMIN");
		config.setPassword("Oracle12345678");
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(2);
		config.setIdleTimeout(10000);
		config.setConnectionTimeout(1000);
		config.setConnectionTestQuery("SELECT 1 FROM dual");
		config.setDataSourceJNDI("jdbc/HikariCP");
		config.setPoolName("{{{ HikariDataSource }}}");
		
		return new HikariDataSource(config);
	} // hikariDataSource


	//============= 2. MyBatis Configuration =============//
	
//	@Bean
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		log.debug("sqlSessionFactory() invoked.");
		
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		
//		bean.setDataSource(this.hikariDataSource());
		bean.setDataSource(dataSource);
		
		//---------------------------------------------------------------------------//
		//-- 1st. method
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		Resource config = resolver.getResource("classpath:mybatis-config.xml");
		
		//-- 2nd. method
//		FileSystemResource config = 
//				new FileSystemResource("C:/app/eclipse/workspace/jee-2022-03/jinterceptor/src/main/resources/mybatis-config.xml");
		
		//-- 3rd. method
//		PathResource config = new PathResource("C:/app/eclipse/workspace/jee-2022-03/jinterceptor/src/main/resources/mybatis-config.xml");
		
		//-- 4th. method (Recommended)
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		
		try(is) {
			InputStreamResource config = new InputStreamResource(is);
			
			bean.setConfigLocation(config);
		} // try-with-resources
		//---------------------------------------------------------------------------//

		//---------------------------------------------------------------------------//
//		만약, 마이바티스 설정파일 내에서, SQL Mapper 파일의 경로를 직접 지정하는 경우에는 아래처럼 생략가능.
//		Resource mapper1 = resolver.getResource("classpath:mappers/1/SQLMapper1.xml");
//		Resource mapper2 = resolver.getResource("classpath:mappers/2/SQLMapper2.xml");
		
//		bean.setMapperLocations(mapper1, mapper2);
		//---------------------------------------------------------------------------//
		
		return bean.getObject();
	} // sqlSessionFactory
	
	
	//===================================================//

	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");
		
	} // destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
	} // afterPropertiesSet

} // end class
