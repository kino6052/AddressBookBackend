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
    public WebResponse apiGetAllGroups() {
//        if (user != null) {
            try {
                List<Group> groupList = groupDao.getUniqueGroups();
                return WebResponse.success(groupList);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
    
    // Create Group
    @WebPost("/api/groups")
    public WebResponse apiCreateGroup(
    						@WebParam("title") String title
                            ) {
//        if (user != null) {
            try {
                Group group = new Group(title, groupDao.getNewGroupId());
                Long newGroupId = groupDao.getNewGroupId();
                group.setGroup_id(newGroupId); // Group Id Has To Be Unique (Regular Id is Not), and Will Be Set at Creation Time to Match Id
                group = groupDao.save(group);
                return WebResponse.success(group);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no create.");
    }

    // Get Group by Id
    @WebGet("/api/groups/{groupId}")
    public WebResponse apiGetGroupById(@PathVar("groupId") Long groupId) {
//        if (user != null) {
            try {
                Group group = groupDao.getGroupById(groupId);
                return WebResponse.success(group);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }

    // Add Contact to Group
    @WebPost("/api/groups/{groupId}")
    public WebResponse apiAddContactToGroup(@WebParam("contactId") Long contactId, @PathVar("groupId") Long groupId) {
//        if (user != null) {
            try {
            	Group group = groupDao.addContactToGroup(groupId, contactId, contactDao);
                if (group != null){
                	return WebResponse.success(group);
                }
                else return WebResponse.fail("Group Already Has This Contact");
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
    
    // Delete Group by Id
    @WebDelete("/api/groups/{groupId}")
    public WebResponse apiDeleteGroupById(@PathVar("groupId") Long groupId) {
//        if (user != null) {
            try {
                groupDao.delete(groupId);
                return WebResponse.success(groupId);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no delete.");
    }
    
    // Get Groups with Contact
    @WebGet("/api/groups/contact/{contactId}")
    public WebResponse apiGetGroupsWithContact(@PathVar("contactId") Long contactId) {
//        if (user != null) {
            try {
                List<Group> groups = groupDao.getGroupsWithContact(contactId);
                return WebResponse.success(groups);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
    
    @WebGet("/api/groups/uniqueId/{uniqueId}")
    public WebResponse apiGetGroupByUniqueId(@PathVar("uniqueId") Long uniqueId) {
//        if (user != null) {
            try {
                Group group = groupDao.getGroupByUniqueId(uniqueId);
                return WebResponse.success(group);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
}