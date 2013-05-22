package org.homebudget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity(name="BINARY_RESOURCE")
public class BinaryResource implements java.io.Serializable{
    @Id
    @Column(name = "RESOURCE_ID")
    @GeneratedValue
    private Long resourceId;
    
    @Column(name="RESOURCE")
    private byte[] resource;
    
    public BinaryResource(){
        
    }
    
    public BinaryResource(byte[] resource){
        this.resource = resource;
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
    
}