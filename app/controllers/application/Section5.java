package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;


public class Section5 extends Controller {
    public static Result get(Long id) {
        int section_num = 5;
        return ok(views.html.application.section5.render(id, section_num));
    }

    public static Result post(long id) {
        return ok("Hello!");
    }
}
