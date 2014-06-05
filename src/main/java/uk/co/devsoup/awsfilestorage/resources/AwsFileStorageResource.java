package uk.co.devsoup.awsfilestorage.resources;

import com.codahale.metrics.annotation.Timed;
import uk.co.devsoup.awsfilestorage.adapters.DomainToInterface;
import uk.co.devsoup.awsfilestorage.domain.Blob;
import uk.co.devsoup.awsfilestorage.interfaces.Listing;
import uk.co.devsoup.awsfilestorage.s3.AWSWrapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/awsfilestorage")
@Produces(MediaType.APPLICATION_JSON)
public class AwsFileStorageResource {
    public AwsFileStorageResource() {

    }

    @GET
    @Timed
    @Path("/")
    public Listing getListing() {
        AWSWrapper w = new AWSWrapper();
        List<Blob> blobs = w.getObjects();
        return new DomainToInterface().convert("", blobs);
    }

    @GET
    @Timed
    @Path("/{path:.*}")
    public Listing getListing(@PathParam("path") String path) {
        AWSWrapper w = new AWSWrapper();
        List<Blob> blobs = w.getObjects(path);
        return new DomainToInterface().convert(path, blobs);
    }
}
