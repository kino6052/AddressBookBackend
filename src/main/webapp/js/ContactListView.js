/**
 * View: ProjectView
 *
 * View showing the project panel and manage all the edits related to a project and its tasks.
 *
 */
(function() {
	
	brite.registerView("ContactListView",{
		create: function(data){
			var view = this;
            view.groupId = data.groupId;
            return app.contactDao.get({}).pipe(function(contacts){
                view.contacts = contacts;
                return render("tmpl-ContactListView",{contacts:contacts});
            });		
		}, 
		postDisplay: function(){},
        events: {
            "click; li[data-entity='Contact']": function(event){
                var view = this;
                var $li = $(event.currentTarget);
                var contactId = $li.attr("data-entity-id");
                var data = {contactId: contactId, groupId: view.groupId};
                view.$el.trigger("DO_ADD_CONTACT_TO_GROUP", data);
            },
            "click; #close": function(event){
                var view = this;
                view.$el.trigger("DO_CLOSE_CONTACT_LIST");
            }
        }
	});
	
	// --------- Private Methods --------- //
    function refresh(){
        var view = this;
        return app.contactDao.get({}).pipe(function(contacts){
            view.contacts = contacts;
            return render("tmpl-ContactListView",{contacts:contacts});
        });	
    }
	// --------- /Private Methods --------- // 
	
})(); 