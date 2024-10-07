package org.zerock.myapp.config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor


@ComponentScan(basePackages = { "org.zerock.myapp.service" })

@MapperScan(basePackages= { "org.zerock.myapp.mapper" })

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
		
//		config.setJdbcUrl("jdbc:oracle:thin:@seoul");
//		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@seoul");
		
//		config.setUsername("HR");
//		config.setPassword("oracle");
		
		
//		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		
//		config.setJdbcUrl("jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
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


	//=========== 2. MyBatis Pooled Data Source Configuration ===========//
	
//	@Primary
//	@Bean
	@Bean(name="pooledDataSource", destroyMethod="forceCloseAll")
	public PooledDataSource pooledDataSource() {
		log.debug("pooledDataSource() invoked.");
		
		PooledDataSource dataSource = new PooledDataSource();
		
//		dataSource.setUrl("jdbc:oracle:thin:@seoul");
//		dataSource.setUrl("jdbc:log4jdbc:oracle:thin:@seoul");
		
//		dataSource.setUsername("HR");
//		dataSource.setPassword("oracle");
		
		
//		dataSource.setDriver("oracle.jdbc.OracleDriver");
		dataSource.setDriver("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		
//		dataSource.setUrl("jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		dataSource.setUrl("jdbc:log4jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		
		dataSource.setUsername("ADMIN");
		dataSource.setPassword("Oracle12345678");
		
		dataSource.setPoolMaximumActiveConnections(5);
		dataSource.setPoolMaximumIdleConnections(2);
		dataSource.setPoolPingEnabled(true);
		dataSource.setPoolPingQuery("SELECT 1 FROM dual");
		dataSource.setLoginTimeout(1);

		return dataSource;
	} // pooledDataSource


	//============= 3. MyBatis Configuration =============//
	
//	@Bean
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		log.debug("sqlSessionFactory() invoked.");
		
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		
//		bean.setDataSource(this.hikariDataSource());
//		bean.setDataSource(this.pooledDataSource());
		
		bean.setDataSource(dataSource);
		
		//---------------------------------------------------------------------------//
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource config = resolver.getResource("classpath:mybatis-config.xml");
		
//		FileSystemResource config = 
//				new FileSystemResource("C:/app/eclipse/workspace/jee-2202-03/jchap07/src/main/resources/mybatis-config.xml");
		
		bean.setConfigLocation(config);
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
