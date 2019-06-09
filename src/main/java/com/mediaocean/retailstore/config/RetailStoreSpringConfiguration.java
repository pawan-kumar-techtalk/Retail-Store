package com.mediaocean.retailstore.config;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@ComponentScan(basePackages = {"com.mediaocean.retailstore"})
public class RetailStoreSpringConfiguration {

    @Autowired
    private RetailStoreConfiguration retailStoreConfiguration;

    @Autowired
    Environment environment;

    @Bean
    public DBI geDatabaseJdbiSession() {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, getDataSourceFactory(), "jdbiFactory");
        return jdbi;
    }


    private DataSourceFactory getDataSourceFactory() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        dataSourceFactory.setDriverClass(retailStoreConfiguration.getDatabase().getDriverClass());
        dataSourceFactory.setUrl(retailStoreConfiguration.getDatabase().getUrl());
        dataSourceFactory.setUser(retailStoreConfiguration.getDatabase().getUser());
        dataSourceFactory.setPassword(retailStoreConfiguration.getDatabase().getPassword());
        return dataSourceFactory;
    }

}
