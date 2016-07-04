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
            return render("tmpl-ContactView");		
		}, 
		
		postDisplay: function(){
			var view = this;
			view.$sectionContent = view.$el.find(".list-container");
		},
        
        docEvents: {
            // SELECT CONTACT: 3) Update the View
            "CONTACT_SELECTION_CHANGE": function(event, extra){
                console.log(extra);
                var view = this;
                view.$sectionContent.bEmpty();
                refreshTable.call(this, extra.contact);
            }
        }
	});
	
	// --------- Private Methods --------- //
	function refreshTable(contact){
		var view = this;
        var contactHtml = render("tmpl-ContactView-list", {contact:contact});
        view.$sectionContent.html(contactHtml); 
	}
	// --------- /Private Methods --------- // 
})(); 