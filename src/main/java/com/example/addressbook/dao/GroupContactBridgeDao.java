package com.example.addressbook.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.britesnow.snow.util.MapUtil;
import com.example.addressbook.dao.GroupDao;
import com.example.addressbook.dao.ContactDao;
import com.example.addressbook.entity.Contact;
import com.example.addressbook.entity.Group;
import com.example.addressbook.entity.GroupContactBridge;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class GroupContactBridgeDao extends BaseHibernateDao<GroupContactBridge> {
	@Inject
	ContactDao contactDao;
	@Inject
	GroupDao groupDao;
	
	public List<Contact> getContactsForGroup(Long groupId){
		List<GroupContactBridge> relationsForGroup = (List<GroupContactBridge>) daoHelper.find(0,  300, "from " + entityClass.getSimpleName() + " where group_id=" + groupId);
		List<Contact> result = new ArrayList<Contact>(); 
		for (GroupContactBridge GroupContact : relationsForGroup){
			Contact contact = contactDao.getContactById(GroupContact.getContact_id());
			if (contact != null) result.add(contact);
		}
		return result;
	}
	
	public List<Group> getGroupsWithContact(Long contactId) {
		List<GroupContactBridge> relationsForContact = (List<GroupContactBridge>) daoHelper.find(0,  300, "from " + entityClass.getSimpleName() + " where contact_id=" + contactId);
		List<Group> result = new ArrayList<Group>(); 
		for (GroupContactBridge GroupContact : relationsForContact){
			Group group = groupDao.getGroupById(GroupContact.getGroup_id());
			if (group != null) result.add(group);
		}
		return result;
	}
	
	public boolean addContactToGroup(Long contactId, Long groupId){
		GroupContactBridge relation = new GroupContactBridge(groupId, contactId);
		if (!entryExists(groupId, contactId)) {
			if (groupExists(groupId) && contactExists(contactId)) {
				save(relation);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		// TODO: This Should be It's Own Method Get Contacts for Group!
		
	}
	
	public boolean removeContactFromGroup(Long contactId, Long groupId){
		GroupContactBridge relation = (GroupContactBridge) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where contact_id=" + contactId + " and group_id=" + groupId);
		if (relation != null){
			delete(relation);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean entryExists(Long groupId, Long contactId){
		GroupContactBridge entry = (GroupContactBridge) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where contact_id=" + contactId + " and group_id=" + groupId);
		if (entry != null) return true;
		else return false;
	}
	
	public boolean groupExists(Long groupId){
		if (groupDao.getGroupById(groupId) != null) return true;
		else return false;
	}

	public boolean contactExists(Long contactId){
		if (contactDao.getContactById(contactId)!= null) return true;
		else return false;
	}
}
	 

