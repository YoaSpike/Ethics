package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;


public class Section1 extends Controller {
    public static Result section1(Long id) {
        int section_num = 1;
        return ok(views.html.application.section1.render(id, section_num));
    }

    public static Result section1_post(long id) {
        return ok("Hello!");
    }
}
