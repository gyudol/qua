package com.mulmeong.batchserver.feed.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.mulmeong.batchserver.feed.infrastructure.repository",
        entityManagerFactoryRef = "feedEntityManagerFactory",
        transactionManagerRef = "feedTransactionManager"
)
public class FeedMysqlDatabaseConfig {

    @Bean(name = "feedDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.feed")
    public DataSource feedDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "feedEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean feedEntityManagerFactory(
            @Qualifier("feedDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.mulmeong.batchserver.feed.domain.entity");
        em.setPersistenceUnitName("feed");
        return getLocalContainerEntityManagerFactoryBean(em);
    }

    static LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean(
            LocalContainerEntityManagerFactoryBean em) {
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        HashMap<String, Object> prop = new HashMap<>();
        prop.put("hibernate.hbm2ddl.auto", "update");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.format_sql", true);
        prop.put("hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        em.setJpaPropertyMap(prop);
        return em;
    }

    @Bean(name = "feedTransactionManager")
    public PlatformTransactionManager feedTransactionManager(
            @Qualifier("feedEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "feedJdbcTemplate")
    public JdbcTemplate feedJdbcTemplate(@Qualifier("feedDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}