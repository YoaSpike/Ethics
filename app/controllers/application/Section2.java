package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.application.*;


public class Section2 extends Controller {
    private static final Form<Section2Form> section2Form = form(Section2Form.class);

    public static Result get(Long id) {
        int section_num = 2;
        return ok(views.html.application.section2.render(id, section_num));
    }

    public static Result post(long id) {
        Form<Section2Form> filledForm = section2Form.bindFromRequest();
        if(filledForm.hasErrors()) {
            return ok(filledForm.errorsAsJson());

        } else {
            return ok(filledForm.get().toString());
        }
    }
}
