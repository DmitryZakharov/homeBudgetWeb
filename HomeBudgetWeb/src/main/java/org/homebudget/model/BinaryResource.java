package org.homebudget.model;

import java.io.IOException;
import java.io.InputStream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;

@Entity(name = "BINARY_RESOURCE")
public class BinaryResource implements java.io.Serializable {

    @Id
    @Column(name = "RESOURCE_ID")
    @GeneratedValue
    private Long resourceId;
    @Lob
    @Column(name = "RESOURCE")
    private byte[] resource;

    public BinaryResource() {
    }

    public BinaryResource(Resource resource) {
        this.resource = getBytesFromResource(resource);
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public byte[] getResource() {
        return resource;
    }

    public void setResource(byte[] resource) {
        this.resource = resource;
    }

    private byte[] getBytesFromResource(Resource resource) {
        byte[] result = {};
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            result = IOUtils.toByteArray(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}