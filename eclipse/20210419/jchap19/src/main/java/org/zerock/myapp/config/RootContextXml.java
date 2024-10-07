package org.zerock.myapp.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor


@EnableAspectJAutoProxy

@EnableTransactionManagement

@MapperScan(basePackages= { "org.zerock.myapp.mapper" })

@ComponentScan(basePackages= { "org.zerock.myapp.service", "org.zerock.myapp.aop" })

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
		config.setPassword("Oracle12345!!!");
		
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
		
		return bean.getObject();
	} // sqlSessionFactory


	//============= 3. TransactionManager Configuration =============//
	
	@Bean(name="transactionManager")
	public TransactionManager transactionManager(DataSource dataSource) {
		log.debug("transactionManager() invoked.");
		
		return new DataSourceTransactionManager(dataSource);
	} // transactionManager
	
	
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
