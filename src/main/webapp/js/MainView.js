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
            brite.display("GroupView", view.$el.find(".MainView-groupViewPanel"));
        }
    });

})(); 