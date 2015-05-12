package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;


public class Section4 extends Controller {
    public static Result section4(Long id) {
        int section_num = 4;
        return ok(views.html.application.section4.render(id, section_num));
    }
}
