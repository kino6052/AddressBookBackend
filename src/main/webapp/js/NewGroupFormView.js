(function(){
    brite.registerView("NewGroupFormView",{
        create: function(data){
            return render("tmpl-NewGroupFormView");
        },
        postDisplay: function(){
            var view = this;
        },
        events: {
            "click; #close": function(event){
                var view = this;
                view.$el.trigger("DO_CLOSE_GROUP_FORM");
            },
            "click; #submit": function(event){
                var view = this;
                var data = {
                    title: view.$el.find("#title").val()
                }
                view.$el.trigger("DO_CREATE_NEW_GROUP", data);
            }
        }
    });
})();