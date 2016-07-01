package com.example.addressbook.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.britesnow.snow.util.MapUtil;
import com.example.addressbook.entity.Group;
import com.example.addressbook.entity.Contact;
import com.example.addressbook.dao.ContactDao;
import com.google.inject.Singleton;

@Singleton
public class GroupDao extends BaseHibernateDao<Group> {
	
	// TODO: How to Find Items Which are Assigned to Multiple Groups?
	public Long getNewGroupId(){
        Group group = (Group) daoHelper.findFirst("from " + entityClass.getSimpleName() + " group by id order by group_id desc" );
        
        if (group != null){
        	return group.getGroup_id() + 1;
        }
        return new Long(1);
    }
	
	// TODO: How to Find Items Which are Assigned to Multiple Groups?
	public Group getGroupById(Long id){
        return (Group) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where group_id = ?", id);
    }
	
	// Since Addresss Book has Many to Many Relationship
	// There Will Be Duplicates, but They Should Have Unique group_id
	public Group getGroupByUniqueId(Long id){
        return (Group) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where id = ?", id);
    }
	
	
	public List<Group> getGroups(){
		return list(0, 300, null, null, null);
	}
	
	// TODO: Return Unique Groups
	// Then, to find associated contactacts, you will have to find all contacts
	// that share the same group_id...
	// Should that be part of contactDao or groupDao?
	public List<Group> getUniqueGroups(){
		@SuppressWarnings("unchecked")
		List<Group> nonUniqueGroups = (List<Group>) daoHelper.find(0, 300, "from " + entityClass.getSimpleName());
		return getUniqueGroups(nonUniqueGroups);
	}
	
	// Get Unique Groups Helper
	// TODO: Write Generic Helper Class for This
	// [QUESTION] How to Use Snow to Group By and Select Distinct?
	private List<Group> getUniqueGroups(List<Group> uniqueGroups) {
		ArrayList<Long> ids = new ArrayList<Long>();
		ArrayList<Group> result = new ArrayList<Group>();
		for (Group group : uniqueGroups){
			Long group_id = group.getGroup_id();
			if (!ids.contains(group_id)){
				result.add(group);
				ids.add(group_id);
			}
		}
		return (List<Group>) result;
	}

	public boolean hasContact(Long group_id, Long contact_id){
		Group group = (Group) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where contact_id=" + contact_id + " and group_id=" + group_id);
		if (group != null) return true;
		else return false;
	}
	
	public Group addContactToGroup(Long group_id, Long contact_id, ContactDao contactDao){
		Group newGroup = null;
		if (!hasContact(group_id, contact_id)){
			Group oldGroup = getGroupById(group_id);
			Contact oldContact = contactDao.getContactById(contact_id);
			if (oldGroup != null) {
				// Create New Group
				newGroup = new Group(oldGroup.getTitle(), group_id);
				newGroup.setContact_id(contact_id);
				newGroup = save(newGroup);
				
				// Create New Contact
				Contact newContact = new Contact(oldContact.getFirst_name(), oldContact.getLast_name(), oldContact.getPhone(), oldContact.getContact_id());
				newContact.setGroup_id(group_id);
				contactDao.save(newContact);
				return newGroup;
			}
		}
		return newGroup;
	}
	
	public List<Group> getGroupsWithContact(Long contactId){
        Map matchProp = MapUtil.mapIt("contact_id", contactId);
        return list(0,300,matchProp,null,null);
    }
}


