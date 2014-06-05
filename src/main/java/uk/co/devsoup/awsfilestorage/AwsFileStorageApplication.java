package uk.co.devsoup.awsfilestorage;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.co.devsoup.awsfilestorage.configuration.AwsFileStorageConfiguration;
import uk.co.devsoup.awsfilestorage.resources.AwsFileStorageResource;

public class AwsFileStorageApplication extends Application<AwsFileStorageConfiguration> {
    public static void main(String[] args) throws Exception {
        new AwsFileStorageApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AwsFileStorageConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AwsFileStorageConfiguration configuration,
                    Environment environment) {
        final AwsFileStorageResource resource = new AwsFileStorageResource();
        environment.jersey().register(resource);
        environment.jersey().register(com.sun.jersey.multipart.impl.MultiPartReaderServerSide.class);
    }
}
