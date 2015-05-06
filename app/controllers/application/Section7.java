package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;


public class Section7 extends Controller {
    public static Result section7(Long id) {
        return ok(views.html.application.section7.render());
    }
}
