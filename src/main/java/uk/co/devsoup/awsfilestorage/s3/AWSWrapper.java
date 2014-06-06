package uk.co.devsoup.awsfilestorage.s3;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.apache.commons.lang.StringUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AWSWrapper {
    private static final String AWS_BUCKET_DELIMITER = "/";

    public List<String> getObjects() {
        return getObjects("");
    }

    public void deleteObject(String path) {
        AmazonS3Client s3client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());
        s3client.deleteObject(System.getenv("AWSFILESTORAGE_BUCKET"), path);
    }

    public List<String> getObjects(String path) {
        List<String> result = new ArrayList<String>();

        AmazonS3Client s3client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());

        ListObjectsRequest lor = new ListObjectsRequest();
        lor.setBucketName(System.getenv("AWSFILESTORAGE_BUCKET"));
        lor.setDelimiter(AWS_BUCKET_DELIMITER);
        if (StringUtils.isNotBlank(path)) {
            lor.setPrefix(path);
        } else {
            lor.setPrefix("");
        }

        ObjectListing ol = s3client.listObjects(lor);

        for (S3ObjectSummary singleObject : ol.getObjectSummaries()) {
            result.add(singleObject.getKey());
        }
        for (String singlePrefix : ol.getCommonPrefixes()) {
            result.add(singlePrefix);
        }

        return result;
    }

    public void createObject(Path source, String location) {
        AmazonS3Client s3client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());
        PutObjectRequest pur = new PutObjectRequest(System.getenv("AWSFILESTORAGE_BUCKET"), location, source.toFile());
        s3client.putObject(pur);
    }
}
