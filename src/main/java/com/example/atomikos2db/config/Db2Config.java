package com.example.atomikos2db.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
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
        basePackages = {"com.example.atomikos2db.repository.db2"},
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager")
public class Db2Config {

    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "datasource.db2")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
            @Qualifier("db2DataSource") final DataSource dataSource) {
        var db2EntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        db2EntityManagerFactory.setDataSource(dataSource);
        db2EntityManagerFactory.setPersistenceUnitName("db2PersistenceUnit");
        db2EntityManagerFactory.setPackagesToScan("com.example.atomikos2db.entity.db2");
        db2EntityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return db2EntityManagerFactory;
    }

    @Bean(name = "db2TransactionManager")
    public JpaTransactionManager db2TransactionManager(
            @Qualifier("db2EntityManagerFactory") final EntityManagerFactory emf) {
        return new JpaTransactionManager();
    }

}
