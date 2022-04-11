package com.example.atomikos2db.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.example.atomikos2db.repository.db1"},
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager")
public class Db1Config {

    @Bean(name = "db1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.db1")
    public DataSource Db1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "db1EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
            @Qualifier("db1DataSource") final DataSource dataSource) {
        var db1EntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        db1EntityManagerFactory.setDataSource(dataSource);
        db1EntityManagerFactory.setPersistenceUnitName("db1PersistenceUnit");
        db1EntityManagerFactory.setPackagesToScan("com.example.atomikos2db.entity.db1");
        db1EntityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return db1EntityManagerFactory;
    }

    @Bean(name = "db1TransactionManager")
    @Primary
    public JpaTransactionManager db1TransactionManager(
            @Qualifier("db1EntityManagerFactory") final EntityManagerFactory emf) {
        return new JpaTransactionManager();
    }

}
