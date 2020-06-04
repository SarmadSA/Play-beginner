package controllers;

import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Admin extends Controller {

    @Before
    public void setConnectedUser(){
        if(Security.isConnected()){
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }

    public static void index() {
        render();
    }

    static void onAuthenticated() {
        Admin.index();
    }

    static void onDisconnected() {
        Application.index();
    }
}
