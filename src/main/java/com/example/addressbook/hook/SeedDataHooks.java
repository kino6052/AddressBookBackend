package com.example.addressbook.hook;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.jdbc.Work;

import com.britesnow.snow.web.db.hibernate.HibernateDaoHelper;
import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.britesnow.snow.web.hook.AppPhase;
import com.britesnow.snow.web.hook.On;
import com.britesnow.snow.web.hook.annotation.WebApplicationHook;
import com.example.addressbook.dao.ContactDao;
import com.example.addressbook.dao.GroupDao;
import com.example.addressbook.dao.UserDao;
import com.example.addressbook.entity.User;
import com.example.addressbook.entity.Group;
import com.example.addressbook.entity.Contact;
import com.google.inject.Singleton;

@Singleton
public class SeedDataHooks {

    /**
     * This will be called to see the database (for demo only)
     * 
     * @param itemDao
     *            will be injected by Snow with the Guice binding
     * @param userDao
     *            will be injected by Snow with the Guice binding
     * @param inView
     *            will be injected by Snow with the Guice binding (needed to open the connection for this thread to use
     *            daoHelper)
     * 
     */
    @WebApplicationHook(phase = AppPhase.INIT)
    public void seedStore(GroupDao groupDao, ContactDao contactDao, UserDao userDao, HibernateSessionInViewHandler inView) {
        inView.openSessionInView();
        for (String[] uvs : seedUsers) {
            String userName = uvs[0];
            if (userDao.getUserByUserName(userName) == null) {
                User newUser = new User(uvs[0], uvs[1], uvs[2], uvs[3]);
                newUser = userDao.save(newUser);
                Long userId = newUser.getId();

                
//                Group group = new Group(defaultGroup);
//                group.setUser_id(userId);
//                group.setGroup_id(group.getId());
//                groupDao.save(group);
//                
//                
//                for (String contactParameter : defaultContacts) {
//                    Contact contact = new Contact();
//                    contact.setUser_id(userId);
//                    contact.setGroup_id(group.getId());
//                    contactDao.save(contact);
//                }
            }
        }
        inView.closeSessionInView();
    }

    /**
     * Using HSQLDB we need to shutdown the database to be written in the file system.
     * 
     * Note that if you do not shutdown your webapp gracefully, the data won't be written to disk. 
     * 
     * @param inView
     * @param daoHelper
     */
    @WebApplicationHook(phase = AppPhase.SHUTDOWN,on=On.BEFORE)
    public void shutdownDb(HibernateSessionInViewHandler inView, HibernateDaoHelper daoHelper){
        try {
            inView.openSessionInView();
            daoHelper.getSession().doWork(new Work() {
                public void execute(Connection con) throws SQLException {
                    con.prepareStatement("shutdown compact").execute();
                }
            });
            inView.closeSessionInView();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
    
    // --------- Some Seed Data --------- //
    private static String[][] seedUsers    = { { "john", "welcome", "John", "Doe" } };

    private static String defaultGroup = "Group001";
    
    private static String[] defaultContacts = { "Contact001", "Contact002", "Contact003" };
    // --------- Some Seed Data --------- //
            

}
