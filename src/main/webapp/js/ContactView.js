/**
 * View: ProjectView
 *
 * View showing the project panel and manage all the edits related to a project and its tasks.
 *
 */
(function() {
	
	brite.registerView("ContactView",{
		
		create: function(data){
			var view = this;
			
			// if the contact is given, then, just render it. 
			if (data.contact){
				view.contact = data.contact;
				return render("tmpl-ContactView",{contact:view.contact});
			}
			// otherwise, we fetch it and return the appropriate promise.
			else{
                // ideally, you would return a contact specified by id, 
                // but for now I just fetch all contacts to display them in the view
				return app.contactsDao.get({}).pipe(function(contacts){
					view.contact = contacts;
					return render("tmpl-ContactView",{contact:contacts});
				});		
			}
		}, 
		
		postDisplay: function(){
			var view = this;
			
			// Persist this element at the view for future use
			view.$sectionContent = view.$el.find("section.content");
			
			refreshTable.call(view); 	
		},		
		
		events: {
			
			// 1) on click of a td .icon-trash of this view
			"click; td .icon-trash": function(event){
				// get the enclosing data-entity="Task" html element to get its id
				var entityRef = $(event.currentTarget).bEntity("Task");
				
				// 2) Delete the task by using the taskDao 
				app.taskDao.delete(entityRef.id);
			} 
		},
		
		daoEvents: {
			// 3) on the dao event dataChange on Task, refresh the task table
			"dataChange; Task": refreshTable
		}
	});
	
	// --------- Private Methods --------- //
	function refreshTable(){
		var view = this;
		
        return app.contactsDao.get({}).done(function(contacts){
           var taskTableHtml = render("tmpl-ContactView-list", {contacts:contacts});
           view.$sectionContent.html(taskTableHtml); 
        });
		// return app.taskDao.list({match:{projectId:view.project.id}}).done(function(taskList){
		// 	var taskTableHtml = render("tmpl-ProjectView-taskList",{tasks:taskList});
		// 	view.$sectionContent.html(taskTableHtml);			
		// });
	}
	// --------- /Private Methods --------- // 
	
})(); 