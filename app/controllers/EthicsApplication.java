package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import com.google.common.io.ByteStreams;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.html.*;
import views.html.forms.*;
import views.formdata.*;
import views.formdata.application.*;
import models.*;


public class EthicsApplication extends Controller {
    public static Result application_summary(Long id) {
        return ok("Summararily");
    }

    private static final Form<NewApplicationForm> newApplicationForm = form(NewApplicationForm.class);

    public static Result new_get() {
        return ok(views.html.application.new_application.render(newApplicationForm));
    }

    public static Result new_post() {

        Form<NewApplicationForm> filledForm = newApplicationForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return ok(views.html.application.new_application.render(filledForm));
        }

    	UserModel user = controllers.Accounts.getCurrentUser();

    	ApplicationModel app = new ApplicationModel();
        app.name = filledForm.get().name;
    	app.userModel = user;

    	// app.section0 = new Section0Model();
    	app.section1 = new Section1Model();
    	app.section2 = new Section2Model();
    	app.section3 = new Section3Model();
    	app.section4 = new Section4Model();
    	// app.section5 = new Section5Model();

    	app.section1.save();
    	app.section2.save();
    	app.section3.save();
    	app.section4.save();

    	app.save();
    	app.refresh(); // get the id back from the db
    	user.applications.add(app);
    	user.save();

    	return redirect(routes.EthicsApplication.application_summary(app.id));
    }
}
