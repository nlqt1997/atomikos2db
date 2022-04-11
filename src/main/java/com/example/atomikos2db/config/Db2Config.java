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
        basePackages = {"com.example.atomikos2db.repository.db2"},
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "transactionManager")
public class Db2Config {

    @Bean(name = "db2DataSource", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "datasource.db2")
    public DataSource Db2DataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "db2EntityManagerFactory")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
            @Qualifier("db2DataSource") final DataSource dataSource,
            final JpaVendorAdapter jpaVendorAdapter
    ) {

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        var db2EntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        db2EntityManagerFactory.setJtaDataSource(dataSource);
        db2EntityManagerFactory.setPersistenceUnitName("db2PersistenceUnit");
        db2EntityManagerFactory.setPackagesToScan("com.example.atomikos2db.entity.db2");
        db2EntityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        db2EntityManagerFactory.setJpaPropertyMap(properties);
        return db2EntityManagerFactory;
    }

}
