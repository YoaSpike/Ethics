package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.application.*;


public class Section1 extends Controller {
    private static final Form<Section1Form> section1Form = form(Section1Form.class);

    public static Result get(Long id) {
        int section_num = 1;
        return ok(views.html.application.section1.render(id, section_num));
    }

    public static Result post(long id) {
        Form<Section1Form> filledForm = section1Form.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(filledForm.errorsAsJson());
        } else {
            return ok("Hello!" + filledForm.get());
        }
    }
}
