package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.application.*;

public class Section0 extends Controller {
    private static final Form<Section0Form> triageForm = form(Section0Form.class);

    private static final int section_num = 0;

    public static Result get(Long id) {
        return get(id, (Section0Form) null);
    }

    public static Result get(Long applicationId, Section0Form data) {
        Form<Section0Form> filledTriageForm = triageForm;

        if (data != null) {
            filledTriageForm = filledTriageForm.fill(data);
            // re-fill with data for editing...
        } else {
            // here we would retrieve a filled form from the database, if available
        }

        return ok(views.html.application.section0.render(applicationId, section_num, filledTriageForm));
    }

    public static Result post(Long id) {
        if (request().body().isMaxSizeExceeded()) {
            return badRequest("Too much data!");
        }

        Form<Section0Form> filledForm = triageForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            System.out.println(triageForm.errorsAsJson());
            return badRequest(views.html.application.section0.render(id, section_num, triageForm));

        } else {
            Section0Form data = filledForm.get();
            System.out.println(data);

            return get(id, data);
        }
    }
}
