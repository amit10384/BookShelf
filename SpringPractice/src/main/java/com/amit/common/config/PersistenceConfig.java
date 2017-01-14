package com.amit.common.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.commons.dbcp.BasicDataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "amitFactory", transactionManagerRef = "amitTransactionalManager", basePackages = {
		"com.amit.persistence.repo"})
public class PersistenceConfig {
	
	@Autowired
	private Environment env;

	@Primary
//	@Profile("production")
	@Bean(name = "bookDataSource")
	public DataSource dataSourceProd() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env
				.getProperty("book.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("book.datasource.url"));
		dataSource.setUsername(env.getProperty("book.datasource.username"));
		dataSource.setPassword(env.getProperty("book.datasource.password"));
		return dataSource;
	}
	

	
	@Primary
	@Bean(name = "amitFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.valueOf(env
				.getProperty("hibernate.amit.generate_ddl")));
		vendorAdapter.setShowSql(Boolean.valueOf(env
				.getProperty("hibernate.amit.show_sql")));
		factory.setDataSource(dataSourceProd());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setJpaDialect(new HibernateJpaDialect());
		factory.setPackagesToScan("com.amit.persistence.entities");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect",
				env.getProperty("spring.jpa.properties.hibernate.dialect"));
		jpaProperties.put("org.hibernate.envers.default_schema",
				env.getProperty("org.hibernate.envers.default_schema"));
		factory.setJpaProperties(jpaProperties);
		factory.setPersistenceUnitName("amitPersistenceUnit");
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory.getObject();
	}
	
	@Bean(name="amitTransactionalManager")
	public PlatformTransactionManager getTransactionalManager() {
		EntityManagerFactory factory = entityManagerFactory();
		return new JpaTransactionManager(factory);
	}
	
	


}
