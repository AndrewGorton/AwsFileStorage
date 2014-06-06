package uk.co.devsoup.awsfilestorage.resources;

import com.codahale.metrics.annotation.Timed;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import uk.co.devsoup.awsfilestorage.interfaces.Listing;
import uk.co.devsoup.awsfilestorage.s3.AWSWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

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
        List<String> blobs = w.getObjects("");
        return new Listing(blobs);
    }

    @GET
    @Timed
    @Path("/{path:.*}")
    public Listing getListing(@PathParam("path") String path, @Context HttpServletRequest req) {
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        AWSWrapper w = new AWSWrapper();
        List<String> blobs = w.getObjects(path);
        return new Listing(blobs);
    }


    @DELETE
    @Timed
    @Path("/{path:.*}")
    public void deleteObject(@PathParam("path") String path) {
        if (path.endsWith("/")) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        }
        new AWSWrapper().deleteObject(path);
    }

    @POST
    @Timed
    @Path("/{path:.*}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadObject(@PathParam("path") String path,
                             @FormDataParam("file") final InputStream fileInputStream,
                             @FormDataParam("file") final FormDataContentDisposition contentDispositionHeader) {
        if (!path.endsWith("/")) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        }
        String fileName = UUID.randomUUID().toString() + "_" + contentDispositionHeader.getFileName();
        java.nio.file.Path outputPath = FileSystems.getDefault().getPath(System.getProperty("java.io.tmpdir"), fileName);
        try {
            Files.copy(fileInputStream, outputPath);
        } catch (IOException e) {
            throw new WebApplicationException(e);
        }

        // Probably should schedule this for an async upload
        new AWSWrapper().createObject(outputPath, path + contentDispositionHeader.getFileName());

        try {
            Files.deleteIfExists(outputPath);
        } catch (IOException e) {
            // Don't care
        }
    }

}
