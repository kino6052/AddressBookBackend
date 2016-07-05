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
            },
            "click; #submit": function(event){
                var view = this;
                var data = {
                    first_name: view.$el.find("#first_name").val(),
                    last_name: view.$el.find("#last_name").val(),
                    phone: view.$el.find("#phone").val()
                }
                view.$el.trigger("DO_CREATE_NEW_CONTACT", data);
            }
        },
        docEvents: {
            "CONTACT_CREATED": function(event){
                var view = this;
                view.$el.trigger("DO_CLOSE_CONTACT_FORM");
            }
        }
    });
})();