/**
 * View: MainView
 * 
 * Main view of the application. Create and manage the sub views. 
 */
(function() {
	
	brite.registerView("MainView",{emptyParent:true},{
		
		create: function(){
			return render("tmpl-MainView");
		},
		
    // Called after the view is displayed to the user
    postDisplay: function(){
      var view = this;
      
			// Create and display the GroupListView view and add it to the .MainView-content
			brite.display("GroupListNav", view.$el.find(".MainView-left"));
            brite.display("ContactView", view.$el.find(".MainView-contactViewPanel"), {});
            brite.display("ContactListView", view.$el.find(".MainView-right"), {});
    },
        
        events: {
            // DOM EVENTS
            "click; #newContact": function(event){
                var view = this;
                view.$el.trigger("DO_OPEN_CONTACT_FORM");   
            },
            
            // APP EVENTS
            // CONTACT SELECTION: 2) DO_SELECT_CONTACT -> CONTACT_SELECTION_CHANGE
            "DO_SELECT_CONTACT": function(event, extra){
              var view = this;
              app.contactDao.get({contactId:extra.contactId}).done(function(contact){
                view.$el.trigger("CONTACT_SELECTION_CHANGE", {contact:contact});      
              })
            },
            
            "DO_OPEN_CONTACT_FORM": function(event){
                var view = this;
                brite.display("NewContactFormView", view.$el.find(".MainView-newContactForm"));
            },
            
            "DO_CLOSE_CONTACT_FORM": function(event){
                var view = this;
                view.$el.find(".MainView-newContactForm").bEmpty();
            }
        }
    });

})(); 