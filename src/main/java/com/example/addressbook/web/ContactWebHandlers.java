package com.example.addressbook.web;

import java.util.List;

import javax.inject.Inject;

import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.param.annotation.WebUser;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.example.addressbook.dao.ContactDao;
import com.example.addressbook.dao.GroupDao;
import com.example.addressbook.entity.Contact;
import com.example.addressbook.entity.Group;
import com.example.addressbook.entity.User;
import com.google.inject.Singleton;

@Singleton
public class ContactWebHandlers {

    @Inject
    public ContactDao contactDao;
    @Inject
    public GroupDao groupDao;

    // Get Contacts
    @WebGet("/api/contacts")
    public WebResponse apiGetContacts(
		@WebParam("contactId") Long contactId
    ) 
    {
        try {
        	// Search Contact by Id
    		if (contactId != null) return WebResponse.success(contactDao.getContactById(contactId));
    		// Get All (Unique) Contacts
    		else return WebResponse.success(contactDao.getUniqueContacts());
        } catch (Throwable t) {
            return WebResponse.fail(t);
        }
    }
    
    // Create Contact
    @WebPost("/api/contacts")
    public WebResponse apiCreateContact(
		@WebParam("first_name") String first_name,
		@WebParam("last_name") String last_name,
		@WebParam("phone") String phone
    )
    {
        try {
            Contact contact = new Contact(first_name, last_name, phone, contactDao.getNewContactId());
            contact = contactDao.save(contact);
            return WebResponse.success(contact);
        } catch (Throwable t) {
            return WebResponse.fail(t);
        }
    }     
}