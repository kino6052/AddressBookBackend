package com.example.addressbook.entity;

import javax.persistence.Entity;

@Entity
public class Group extends BaseEntity {

    private String title;
    private Long   contact_id;
    private Long   group_id;
    private Long   user_id;
    
    
    //private User user;
    public Group(){
    	
    }
    
    public Group(String title, Long group_id){
    	this.title = title;
    	this.group_id = group_id;
    }
    
    
    /*
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    */
    
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Long getContact_id(){
    	return contact_id;
    }
    
    public void setContact_id(Long contactId){
    	this.contact_id = contactId;
    }
    
    public Long getGroup_id(){
    	return group_id;
    }
    
    public void setGroup_id(Long groupId){
    	this.group_id = groupId;
    }
    
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }    
    
}