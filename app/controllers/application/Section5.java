package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;


public class Section5 extends Controller {
    public static Result section5(Long id) {
        return ok(views.html.application.section5.render());
    }
}
