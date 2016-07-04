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
			
			// if the contact is given, then, just render it. 
			if (data.contact){
				view.contact = data.contact;
				return render("tmpl-ContactListView",{contacts:view.contact});
			}
			// otherwise, we fetch it and return the appropriate promise.
			else{
                // ideally, you would return a contact specified by id, 
                // but for now I just fetch all contacts to display them in the view
				return app.contactDao.get({}).pipe(function(contacts){
					view.contact = contacts;
					return render("tmpl-ContactListView",{contacts:contacts});
				});		
			}
		}, 
		
		postDisplay: function(){
			var view = this;
			
			// Persist this element at the view for future use
			 view.$sectionContent = view.$el.find(".list-container");
			
			 refreshTable.call(view); 	
		}
	});
	
	// --------- Private Methods --------- //
	function refreshTable(){
		var view = this;
		
        return app.contactDao.get({}).done(function(contacts){
           var taskTableHtml = render("tmpl-ContactListView-list", {contacts:contacts});
           view.$sectionContent.html(taskTableHtml); 
        });
		// return app.taskDao.list({match:{projectId:view.project.id}}).done(function(taskList){
		// 	var taskTableHtml = render("tmpl-ProjectView-taskList",{tasks:taskList});
		// 	view.$sectionContent.html(taskTableHtml);			
		// });
	}
	// --------- /Private Methods --------- // 
	
})(); 