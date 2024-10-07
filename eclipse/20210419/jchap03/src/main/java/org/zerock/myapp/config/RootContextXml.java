package org.zerock.myapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor

@Configuration("root-context.xml")
public class RootContextXml
	implements InitializingBean, DisposableBean {
	
	
	
//	@Bean
	@Bean(name="hikariDataSource", destroyMethod="close")
	public DataSource dataSource() {
		log.debug("dataSource() invoked.");
		
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		config.setUsername("HR");
		config.setPassword("Oracle12345!!!");
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(2);
		config.setIdleTimeout(10000);
		config.setConnectionTimeout(1000);
		config.setConnectionTestQuery("SELECT 1 FROM dual");
		config.setDataSourceJNDI("jdbc/HikariCP");
		config.setPoolName("*** HikariDataSource ***");
		
		return new HikariDataSource(config);
	} // dataSource
	
	
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
