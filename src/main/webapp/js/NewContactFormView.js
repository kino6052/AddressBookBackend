(function(){
    brite.registerView("NewContactFormView",{
        create: function(data){
            return render("tmpl-NewContactFormView");
        },
        postDisplay: function(){
            var view = this;
        },
        events: {
            "click; #close": function(event){
                var view = this;
                view.$el.trigger("DO_CLOSE_CONTACT_FORM");
            }
        }
    });
})();