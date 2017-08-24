package pl.poleng.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.poleng.dao", repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
@PropertySource("classpath:datasource.properties")
public class DatabaseConfig {

	@Value("${bonecp.url}")
	private String jdbcUrl;

	@Value("${bonecp.username}")
	private String jdbcUsername;

	@Value("${bonecp.password}")
	private String jdbcPassword;

	@Value("${bonecp.driverClass}")
	private String driverClass;

	@Value("${bonecp.idleMaxAgeInMinutes}")
	private Integer idleMaxAgeInMinutes;

	@Value("${bonecp.idleConnectionTestPeriodInMinutes}")
	private Integer idleConnectionTestPeriodInMinutes;

	@Value("${bonecp.maxConnectionsPerPartition}")
	private Integer maxConnectionsPerPartition;

	@Value("${bonecp.minConnectionsPerPartition}")
	private Integer minConnectionsPerPartition;

	@Value("${bonecp.partitionCount}")
	private Integer partitionCount;

	@Value("${bonecp.acquireIncrement}")
	private Integer acquireIncrement;

	@Value("${bonecp.statementsCacheSize}")
	private Integer statementsCacheSize;

	@Bean(destroyMethod = "close")
	public DataSource mainDataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
		dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
		dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
		dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
		dataSource.setPartitionCount(partitionCount);
		dataSource.setAcquireIncrement(acquireIncrement);
		dataSource.setStatementsCacheSize(statementsCacheSize);
		return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(mainDataSource());
		emf.setPackagesToScan("pl.poleng.dao.model");
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		emf.setPersistenceUnitName("persistenceUnit");
		emf.setJpaDialect(new HibernateJpaDialect());

		Properties jpaProperties = new Properties();
		// jpaProperties.put("hibernate.show_sql", "true");
		// jpaProperties.put("hibernate.format_sql", "true");
		// jpaProperties.put("hibernate.ejb.naming_strategy",
		// "org.hibernate.cfg.ImprovedNamingStrategy");
		// jpaProperties.put("hibernate.connection.charSet", "UTF-8");
		// jpaProperties.put("hibernate.dialect",
		// "org.hibernate.dialect.MySQLDialect");
		jpaProperties.put("hibernate.cache.use_second_level_cache", "true");
		jpaProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		jpaProperties.put("hibernate.cache.use_query_cache", "true");
		jpaProperties.put("hibernate.generate_statistics", "true");
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");	

		emf.setJpaProperties(jpaProperties);
		emf.afterPropertiesSet();

		return emf.getObject();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		//jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
		
		//jpaVendorAdapter.setGenerateDdl(true);		
		jpaVendorAdapter.setShowSql(true);

		return jpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}

}