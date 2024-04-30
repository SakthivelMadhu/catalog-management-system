package com.example.catalog.repository;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

@Configuration
@EnableJpaRepositories("com.example.catalog.repository")
@EnableTransactionManagement
public class JpaConfig {
    private final DataSource dataSource;
    
    public JpaConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, JpaProperties jpaProperties) {
        return builder.dataSource(dataSource)
                      .packages("com.example.catalog.entity") // Package where your entities are located
                      .properties(jpaProperties.getProperties())
                      .build();
    }
}
