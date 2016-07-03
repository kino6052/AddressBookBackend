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
import com.example.addressbook.dao.GroupContactBridgeDao;
import com.google.inject.Singleton;

@Singleton
public class GroupContactBridgeWebHandlers {

    @Inject
    public ContactDao contactDao;
    @Inject
    public GroupDao groupDao;
    @Inject
    public GroupContactBridgeDao groupContactBridgeDao;
    
    // Get Contacts for Group
    @WebGet("/api/contacts/group")
    public WebResponse apiGetContactsForGroup(
		@WebParam("groupId") Long groupId
    ) 
    {
        try {
        	// Check for Parameter
    		if (groupId != null) return WebResponse.success(groupContactBridgeDao.getContactsForGroup(groupId));
    		// No Parameter
    		else return WebResponse.fail("No Parameter groupId Provided");
        } catch (Throwable t) {
            return WebResponse.fail(t);
        }
    }
    
    // Get Groups with Contact
    @WebGet("/api/groups/contact")
    public WebResponse apiGetGroupsWithContact(
		@WebParam("contactId") Long contactId
    ) 
    {
        try {
        	// Check for Parameter
    		if (contactId != null) return WebResponse.success(groupContactBridgeDao.getGroupsWithContact(contactId));
    		// No Parameter
    		else return WebResponse.fail("No Parameter contactId Provided");
        } catch (Throwable t) {
            return WebResponse.fail(t);
        }
    }
    
    // Add Contact to Group
    @WebPost("/api/addContactToGroup")
    public WebResponse apiAddGroupToContact(
		@WebParam("groupId") Long groupId,
		@WebParam("contactId") Long contactId
    )
    {
    	try {
    		if (groupId != null && contactId != null) return WebResponse.success(groupContactBridgeDao.addContactToGroup(contactId, groupId));
    		else return WebResponse.fail("No Parameter groupId and/or contactId Provided");
    	} catch (Throwable t){
    		return WebResponse.fail(t);
    	}
    }
    
    // Add Contact to Group
    @WebDelete("/api/removeContactFromGroup")
    public WebResponse apiRemoveContactFromGroup(
		@WebParam("groupId") Long groupId,
		@WebParam("contactId") Long contactId
    )
    {
    	try {
    		if (groupId != null && contactId != null) return WebResponse.success(groupContactBridgeDao.removeContactFromGroup(contactId, groupId));
    		else return WebResponse.fail("No Parameter groupId and/or contactId Provided");
    	} catch (Throwable t){
    		return WebResponse.fail(t);
    	}
    }
    
}