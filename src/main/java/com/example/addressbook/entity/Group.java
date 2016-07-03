package com.example.addressbook.entity;

import javax.persistence.Entity;

@Entity
public class Group extends BaseEntity {

    private String title;
    private Long   group_id;
    
    
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
    
    public Long getGroup_id(){
    	return group_id;
    }
    
    public void setGroup_id(Long groupId){
    	this.group_id = groupId;
    }  
    
}