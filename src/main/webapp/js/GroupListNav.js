/**
 * View: ProjectListNav
 *
 * Responsibilities:
 *   - Manage the list of groups (create, delete, select, rename)
 *
 */
(function() {
	
	brite.registerView("GroupListNav",{
		
		create: function(){
			return render("tmpl-GroupListNav");
		},
		
		postDisplay: function(){
			var view = this;
			
			// caching for future use
			view.$listContainer = view.$el.find(".list-container");

			// call the view's private method to refresh the project list. 			
			refreshList.call(view);
		}
	});
		
	// Private view method: refresh the group list. 
	function refreshList(){
		var view = this;
		
		// from the groupDao, list all the groups, and when done
		// update the view.$listContainer with the new HTML elements
		app.groupDao.get().done(function(groups){
			view.$listContainer.html(render("tmpl-GroupListNav-list",{groups:groups}));
		});		
	}	

})(); 