package uk.co.devsoup.simpledropwizardecho;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import uk.co.devsoup.simpledropwizardecho.configuration.SimpleDropWizardEchoConfiguration;
import uk.co.devsoup.simpledropwizardecho.resources.SimpleDropWizardEchoResource;

public class SimpleDropWizardEchoApplication extends Application<SimpleDropWizardEchoConfiguration> {
    public static void main(String[] args) throws Exception {
        new SimpleDropWizardEchoApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<SimpleDropWizardEchoConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(SimpleDropWizardEchoConfiguration configuration,
                    Environment environment) {
        final SimpleDropWizardEchoResource resource = new SimpleDropWizardEchoResource();
        environment.jersey().register(resource);
    }
}
