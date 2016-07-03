var app = app || {};

(function(){
    app.contactDao      = brite.registerDao(new RemoteDaoHandler("contacts"));
    app.groupDao        = brite.registerDao(new RemoteDaoHandler("groups"));
})();