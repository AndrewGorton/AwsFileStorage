package uk.co.devsoup.awsfilestorage.adapters;

import org.apache.commons.lang.StringUtils;
import uk.co.devsoup.awsfilestorage.domain.Blob;
import uk.co.devsoup.awsfilestorage.domain.File;
import uk.co.devsoup.awsfilestorage.interfaces.Listing;

import java.util.ArrayList;
import java.util.List;

public class DomainToInterface {

    public uk.co.devsoup.awsfilestorage.interfaces.File convert(uk.co.devsoup.awsfilestorage.domain.File from) {
        uk.co.devsoup.awsfilestorage.interfaces.File to = new uk.co.devsoup.awsfilestorage.interfaces.File(from.getName());
        return to;
    }

    public uk.co.devsoup.awsfilestorage.interfaces.Folder convert(uk.co.devsoup.awsfilestorage.domain.Folder from) {
        uk.co.devsoup.awsfilestorage.interfaces.Folder to = new uk.co.devsoup.awsfilestorage.interfaces.Folder(from.getName());
        return to;
    }

    public Listing convert(String path, List<uk.co.devsoup.awsfilestorage.domain.Blob> from) {
        Boolean pathExists = false;
        if (StringUtils.isBlank(path)) {
            pathExists = true;
        }
        List<uk.co.devsoup.awsfilestorage.interfaces.File> outFiles = new ArrayList<uk.co.devsoup.awsfilestorage.interfaces.File>();
        List<uk.co.devsoup.awsfilestorage.interfaces.Folder> outFolders = new ArrayList<uk.co.devsoup.awsfilestorage.interfaces.Folder>();
        for (Blob singleBlob : from) {
            if (singleBlob instanceof uk.co.devsoup.awsfilestorage.domain.File) {
                if (singleBlob.getName().compareTo(path) == 0) {
                    pathExists = true;
                }
                if (!StringUtils.endsWith(singleBlob.getName(), "/")) {
                    outFiles.add(convert((uk.co.devsoup.awsfilestorage.domain.File) singleBlob));
                }
            } else if (singleBlob instanceof uk.co.devsoup.awsfilestorage.domain.Folder) {
                outFolders.add(convert((uk.co.devsoup.awsfilestorage.domain.Folder) singleBlob));
            } else {
                throw new RuntimeException("Unknown type");
            }
        }

        if (!pathExists) {
            outFolders.clear();
        }
        return new Listing(path, outFiles, outFolders, pathExists);
    }
}
