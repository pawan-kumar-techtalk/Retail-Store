package com.mediaocean.retailstore.config;

import com.codahale.metrics.health.HealthCheckRegistry;
import io.dropwizard.setup.Environment;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringContextConfigurer {

    public ApplicationContext configure(RetailStoreConfiguration retailConfiguration, HealthCheckRegistry healthCheckRegistry,
                                        Environment environment) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setParent(startParentContext(retailConfiguration, healthCheckRegistry, environment));
        applicationContext.register(RetailStoreSpringConfiguration.class);
        applicationContext.refresh();
        applicationContext.registerShutdownHook();
        applicationContext.start();

        return applicationContext;
    }

    private ApplicationContext startParentContext(RetailStoreConfiguration retailConfiguration, HealthCheckRegistry healthCheckRegistry,
                                                  Environment environment) {
        AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
        parent.refresh();
        parent.getBeanFactory().registerSingleton("configuration", retailConfiguration);
        parent.getBeanFactory().registerSingleton("environmentObject", environment);
        parent.getBeanFactory().registerSingleton("healthCheckRegistry", healthCheckRegistry);
        parent.registerShutdownHook();
        parent.start();

        return parent;
    }
}
