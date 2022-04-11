package com.example.atomikos2db.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
        basePackages = {"com.example.atomikos2db.repository.db1"},
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "transactionManager")
public class Db1Config {

    @Bean(name = "db1DataSource", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "datasource.db1")
    public DataSource Db1DataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "db1EntityManagerFactory")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
            @Qualifier("db1DataSource") final DataSource dataSource,
            final JpaVendorAdapter jpaVendorAdapter
    ) {

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        var db1EntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        db1EntityManagerFactory.setJtaDataSource(dataSource);
        db1EntityManagerFactory.setPersistenceUnitName("db1PersistenceUnit");
        db1EntityManagerFactory.setPackagesToScan("com.example.atomikos2db.entity.db1");
        db1EntityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        db1EntityManagerFactory.setJpaPropertyMap(properties);
        return db1EntityManagerFactory;
    }

}
