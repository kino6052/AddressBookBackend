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
    
    @WebGet("/api/contacts")
    public WebResponse apiGetContacts() {
//        if (user != null) {
            try {
                List<Contact> contactList = contactDao.getUniqueContacts();
                return WebResponse.success(contactList);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
    
    @WebPost("/api/contacts")
    public WebResponse apiCreateItem(
    						@WebParam("first_name") String first_name,
    						@WebParam("last_name") String last_name,
    						@WebParam("phone") String phone
                            ) {
//        if (user != null) {
            try {
                Contact contact = new Contact(first_name, last_name, phone, contactDao.getNewContactId());
                contact = contactDao.save(contact);
                return WebResponse.success(contact);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no create.");
    }

    @WebDelete("/api/contacts/{contactId}")
    public WebResponse apiDeleteItemById(@WebUser User user, @PathVar("contactId") Long contactId) {
//        if (user != null) {
            try {
                contactDao.delete(contactId);
                return WebResponse.success(contactId);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no delete.");
    }

    @WebGet("/api/contacts/group/{groupId}")
    public WebResponse apiGetContactsFromGroup(@WebUser User user, @PathVar("groupId") Long groupId) {
//        if (user != null) {
            try {
                List<Contact> contacts = contactDao.getContactsForGroup(groupId);
                return WebResponse.success(contacts);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
    
    @WebGet("/api/contacts/{contactId}")
    public WebResponse apiGetContactById(@WebUser User user, @PathVar("contactId") Long contactId) {
//        if (user != null) {
            try {
                Contact contact = contactDao.getContactById(contactId);
                return WebResponse.success(contact);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
    
    // Add Contact to Group
    @WebPost("/api/contacts/{contactId}")
    public WebResponse apiAddContactToGroup(@WebParam("groupId") Long groupId, @PathVar("contactId") Long contactId) {
//        if (user != null) {
            try {
            	Contact contact = contactDao.addGroupToContact(contactId, groupId, groupDao);
                if (contact != null){
                	return WebResponse.success(contact);
                }
                else return WebResponse.fail("Group Already Has This Contact");
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
}