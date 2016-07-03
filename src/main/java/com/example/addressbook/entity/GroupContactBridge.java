package com.example.addressbook.entity;

import javax.persistence.Entity;

@Entity
public class GroupContactBridge extends BaseEntity {
	private Long   group_id;
    private Long   contact_id;
    
    //private User user;
    public GroupContactBridge(){
    	
    }
    
    public GroupContactBridge(Long group_id, Long contact_id){
    	this.group_id = group_id;
    	this.contact_id = contact_id;
    }
    
    public Long getGroup_id(){
    	return group_id;
    }
    
    public void setGroup_id(Long groupId){
    	this.group_id = groupId;
    }  
    
    public Long getContact_id(){
    	return contact_id;
    }
    
    public void setContact_id(Long contactId){
    	this.contact_id = contactId;
    }  
    
}