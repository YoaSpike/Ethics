package controllers;

import java.util.Map;
import java.util.List;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.html.forms.*;
import views.formdata.*;
import models.*;


public class Forms extends Controller {
    public static Result index() {
        List<ApplicationModel> things = (
            ApplicationModel.find
            .where()
                .eq("userModel", controllers.Accounts.getCurrentUser())
            .query()
            .findList()
        );

        return ok(forms_index.render(things));
    }

    @Security.Authenticated(business.Secured.class)
    public static Result viewall() {
        List<ApplicationModel> forms = (
            ApplicationModel.find
            .where()
                .eq("userModel", controllers.Accounts.getCurrentUser())
            .query()
            .findList()
        );

        return ok(views.html.viewall.render(forms));
    }
}
