package uk.co.devsoup.awsfilestorage.s3;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.apache.commons.lang.StringUtils;
import uk.co.devsoup.awsfilestorage.domain.Blob;
import uk.co.devsoup.awsfilestorage.domain.File;
import uk.co.devsoup.awsfilestorage.domain.Folder;

public class AWSWrapper {
    private static final String AWS_BUCKET_DELIMITER = "/";

    public List<Blob> getObjects() {
        return getObjects("");
    }

    public List<Blob> getObjects(String path) {
        List<Blob> result = new ArrayList<Blob>();

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
            File tempFile = new File();
            tempFile.setName(singleObject.getKey());
            result.add(tempFile);
        }
        for (String singlePrefix : ol.getCommonPrefixes()) {
            Folder tempFolder = new Folder();
            tempFolder.setName(singlePrefix);
            result.add(tempFolder);
        }

        return result;
    }
}
