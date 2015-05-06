package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;


public class Section3 extends Controller {
    public static Result section3(Long id) {
        return ok(views.html.application.section3.render());
    }
}
