package com.example.addressbook.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.britesnow.snow.util.MapUtil;
import com.example.addressbook.dao.GroupDao;
import com.example.addressbook.entity.Contact;
import com.example.addressbook.entity.Group;
import com.google.inject.Singleton;

@Singleton
public class ContactDao extends BaseHibernateDao<Contact> {
	
	// Return Unique Contacts
	public List<Contact> getUniqueContacts(){
		@SuppressWarnings("unchecked")
		List<Contact> nonUniqueContacts = (List<Contact>) daoHelper.find(0, 300, "from " + entityClass.getSimpleName());
		return getUniqueContacts(nonUniqueContacts);
	}
	
	// Get Unique Contacts Helper
	// TODO: Write Generic Helper Class for This
	// [QUESTION] How to Use Snow to Group By and Select Distinct?
	private List<Contact> getUniqueContacts(List<Contact> uniqueContacts) {
		ArrayList<Long> ids = new ArrayList<Long>();
		ArrayList<Contact> result = new ArrayList<Contact>();
		for (Contact contact: uniqueContacts){
			Long contact_id = contact.getContact_id();
			if (!ids.contains(contact_id)){
				result.add(contact);
				ids.add(contact_id);
			}
		}
		return (List<Contact>) result;
	}
	
	public Contact getContactById(Long id){
        return (Contact) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where contact_id = ?", id);
    }
	
	public Contact getContactByUniqueId(Long id){
        return (Contact) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where id = ?", id);
    }
	
	public List<Contact> getContacts(){
		return list(0,300,null,null,null);
    }
	
    public List<Contact> getContactsForGroup(Long group_id){
        Map matchProp = MapUtil.mapIt("group_id", group_id);
        return list(0,300,matchProp,null,null);
    }
    
    
 	public Long getNewContactId(){
         Contact contact = (Contact) daoHelper.findFirst("from " + entityClass.getSimpleName() + " group by id order by contact_id desc" );
         
         if (contact != null){
         	return contact.getGroup_id() + 1;
         }
         return new Long(1);
     }
    
    // ------- Helper Functions ------ //
    
    // Check if a Contact Belongs to a Group
    public boolean hasGroup(Long contact_id, Long group_id){
		Contact contact = (Contact) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where group_id=" + contact_id + " and contact_id=" + group_id);
		if (contact != null) return true;
		else return false;
	}
	
	public Contact addGroupToContact(Long group_id, Long contact_id, GroupDao groupDao){
		Contact newContact = null;
		if (!hasGroup(contact_id, group_id)){
			Group oldGroup = groupDao.getGroupById(group_id);
			Contact oldContact = getContactById(contact_id);
			if (oldContact != null) {
				// Create New Contact
				newContact = new Contact(oldContact.getFirst_name(), oldContact.getLast_name(), oldContact.getPhone(), contact_id);
				newContact.setGroup_id(group_id);
				newContact = save(newContact);
				
				// Create New Group
				Group newGroup = new Group(oldGroup.getTitle(), group_id);
				newGroup.setContact_id(contact_id);
				groupDao.save(newGroup);
				return newContact;
			}
		}
		return newContact;
	}

}
	

