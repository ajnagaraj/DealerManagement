package com.powerhaus.brookleaf.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.*;
import static org.springframework.orm.jpa.vendor.Database.*;

@Configuration
@EnableJpaRepositories(basePackages = "com.powerhaus.brookleaf.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
    private static final String JNDI_NAME = "jdbc/powerhaus";
    
    @Profile("production")
    @Bean(name = "dataSource")
    public DataSource jndiBasedDataSource() {
        JndiObjectFactoryBean jndiFactory = new JndiObjectFactoryBean();
    
        jndiFactory.setJndiName(JNDI_NAME);
        jndiFactory.setResourceRef(true);
        jndiFactory.setProxyInterface(javax.sql.DataSource.class);
        
        return (DataSource) jndiFactory.getObject();
    }
    
    @Profile("integration")
    @Bean(name = "dataSource")
    public DataSource pooledDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/powerhaus");
        basicDataSource.setUsername("mars");
        basicDataSource.setPassword("Earth2016$");
        
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxActive(10);
        
        return basicDataSource;
    }
    
    @Profile("development")
    @Bean(name = "dataSource")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("database/sql/schema.sql")
                .addScript("database/sql/test-data.sql")
                .build();
    }
    
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        
        jpaVendorAdapter.setDatabase(MYSQL);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        
        return jpaVendorAdapter;
    }
    
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("com.powerhaus.brookleaf.entity");
        
        return entityManagerFactoryBean;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
