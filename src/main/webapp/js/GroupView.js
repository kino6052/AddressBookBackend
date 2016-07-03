/**
 * View: ProjectListNav
 *
 * Responsibilities:
 *   - Manage the list of project (create, delete, select, rename)
 *
 */
(function() {
	
	brite.registerView("GroupView",{
		
		create: function(){
			return render("tmpl-GroupView");
		},
		
		postDisplay: function(){
			var view = this;
			
			// caching for future use
			view.$listContainer = view.$el.find(".list-container");

			// call the view's private method to refresh the project list. 			
			refreshList.call(view);
		}
	});
		
	// Private view method: refresh the project list. 
	function refreshList(){
		var view = this;
		
		// from the projectDao, list all the project, and when done
		// update the view.$listContainer with the new HTML elements
		app.contactDao.get().done(function(contacts){
			view.$listContainer.html(render("tmpl-GroupView-list",{contacts:contacts}));
		});		
	}	

})(); 