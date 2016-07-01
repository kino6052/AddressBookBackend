package com.example.addressbook.entity;

import javax.persistence.Entity;

@Entity
public class Contact extends BaseEntity {

    private String first_name;
    private String last_name;
    private String phone;
    private Long   group_id;
    private Long   contact_id;
    private Long   user_id;
    
    //private User user;
    
    public Contact(){
    	
    }
    
    public Contact(String first_name, String last_name, String phone, Long contact_id){
    	this.contact_id = contact_id;
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.phone = phone;
    }
    
    /*
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    */
    
    
    public String getFirst_name() {
        return first_name;
    }
    
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    
    public String getLast_name() {
        return last_name;
    }
    
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
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