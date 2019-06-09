package com.mediaocean.retailstore.config;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.springframework.context.ApplicationContext;

public class RetailStoreApplication extends Application<RetailStoreConfiguration> {

    private final SpringContextConfigurer springContextConfigurer;

    @Override
    public String getName() {
        return "retail-store";
    }

    public RetailStoreApplication(SpringContextConfigurer springContextConfigurer) {
        this.springContextConfigurer = springContextConfigurer;
    }

    public RetailStoreApplication() {
        this(new SpringContextConfigurer());
    }

    public static void main(String[] args) throws Exception {
        new RetailStoreApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RetailStoreConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new SwaggerBundle<RetailStoreConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(RetailStoreConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(RetailStoreConfiguration configuration, Environment environment) throws Exception {

        ApplicationContext applicationContext =
                springContextConfigurer.configure(configuration, environment.healthChecks(), environment);
        ApplicationConfigurer applicationConfigurer = applicationContext.getBean(ApplicationConfigurer.class);
        applicationConfigurer.configure(environment);

        environment.jersey().setUrlPattern("/media-ocean/retail-store/*");
    }
}
