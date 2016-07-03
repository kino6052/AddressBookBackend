package com.example.addressbook.web;

import java.util.List;

import javax.inject.Inject;

import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.param.annotation.WebUser;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.example.addressbook.dao.GroupDao;
import com.example.addressbook.dao.ContactDao;
import com.example.addressbook.entity.Contact;
import com.example.addressbook.entity.Group;
import com.example.addressbook.entity.User;
import com.google.inject.Singleton;

@Singleton
public class GroupWebHandlers {

    @Inject
    public GroupDao groupDao;
    @Inject
    public ContactDao contactDao;

    // Get All Groups
    @WebGet("/api/groups")
    public WebResponse apiGetAllGroups(
    	@WebParam("groupId") Long groupId
	) 
    {
        try {
        	// Search Group by Id
        	if (groupId != null) return WebResponse.success(groupDao.getGroupById(groupId));
        	// Search Unique Groups
        	else return WebResponse.success(groupDao.getUniqueGroups()); 
        } catch (Throwable t) {
            return WebResponse.fail(t);
        }
    }
    
    // Create Group
    @WebPost("/api/groups")
    public WebResponse apiCreateGroup(
		@WebParam("title") String title
    ) 
    {
        try {
            Group group = new Group(title, groupDao.getNewGroupId());
            group = groupDao.save(group);
            return WebResponse.success(group);
        } catch (Throwable t) {
            return WebResponse.fail(t);
        }
    }
}