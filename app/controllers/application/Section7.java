package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;


public class Section7 extends Controller {
    private static final Form<Section7Form> section7Form = form(Section7Form.class);

    public static Result get(Long id) {
        int section_num = 7;
        return ok(views.html.application.section7.render(id, section_num));
    }

    public static Result post(long id) {
        Form<Section7Form> filledForm = section7Form.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(filledForm.errorsAsJson());
        } else {
            java.util.Map<String,String> data = filledForm.data();

            return ok(play.libs.Json.toJson(data).toString());
        }
    }
}
