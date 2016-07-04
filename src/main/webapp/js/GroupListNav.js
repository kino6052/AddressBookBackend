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
		},
        
        events: {
            // GROUP SELECTION: 1) Click on <a>s element
            "click; li[data-entity='Group'] > a": function(event){
                var $a = $(event.currentTarget);
                var groupId = $a.attr("data-entity-id");
                this.$el.trigger("DO_SELECT_GROUP", {groupId:groupId});
            }, 
            
            // CONTACT SELECTION: 1) Click on <li> element
            "click; li[data-entity='Contact']": function(event){
                var $li = $(event.currentTarget);
                var contactId = $li.attr("data-entity-id");
                this.$el.trigger("DO_SELECT_CONTACT", {contactId:contactId});
            }
        },
        docEvents: {
            // GROUP SELECTION: 2) DO_GROUP_CHANGE -> GROUP_SELECTION_CHANGE
            "DO_SELECT_GROUP": function(event, extra){
                var view = this;
                view.$el.trigger("GROUP_SELECTION_CHANGE", {groupId:extra.groupId}); 
            },
            
            // CONTACT SELECTION: 2) DO_SELECT_CONTACT -> CONTACT_SELECTION_CHANGE (Inside MainView.js)
                        
            // GROUP SELECTION: 3) Make the selected group name bold
            "GROUP_SELECTION_CHANGE": function(event, extra){
                var view = this;
                showGroupSelected.call(this, extra.groupId);
                //$listContainer = view.$el.find("li[data-entity-id='" + extra.group.id + "'] > .contactList-container" ).bEmpty();
                view.$el.find(".contactList-container").bEmpty();
                refreshList.call(view, extra.groupId, 'group');
            }   
        }
	});
		
    function showGroupSelected(groupId){
        var view = this;
        
        // deselect (remove bold styling) currently selected group
        view.$el.find("li.sel").removeClass("sel");
        
        var $selectedLi = view.$el.find("li[data-entity-id='" + groupId + "']");
        $selectedLi.addClass("sel");
    }    
    
	// Private view method: refresh the group list. 
	function refreshList(groupId, accessEntity){
		var view = this;
		
		// from the groupDao, list all the groups, and when done
		// update the view.$listContainer with the new HTML elements
		app.groupDao.get().done(function(groups){
			view.$listContainer.html(render("tmpl-GroupListNav-list",{groups:groups}));
		})
 
        .then(function(){
            app.contactDao.get({groupId:groupId}, accessEntity).done(function(contacts){
                console.log(contacts);
               view.$contactListContainer = view.$el.find("a[data-entity-id='"+groupId+"'] + .contactList-container");
               view.$contactListContainer.html(render("tmpl-GroupListNav-contactList",{contacts:contacts})); 
            });
        });		
	}	

})(); 