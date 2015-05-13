package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;

public class Section0 extends Controller {
    private static final Form<EthicsTriageForm> triageForm = form(EthicsTriageForm.class);

    private static final int section_num = 0;

    public static Result get(Long id) {
        return get(id, (EthicsTriageForm) null);
    }

    public static Result get(Long applicationId, EthicsTriageForm data) {
        Form<EthicsTriageForm> filledTriageForm = triageForm;

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

        Form<EthicsTriageForm> filledForm = triageForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            System.out.println(triageForm.errorsAsJson());
            return badRequest(views.html.application.section0.render(id, section_num, triageForm));

        } else {
            EthicsTriageForm data = filledForm.get();
            System.out.println(data);

            return get(id, data);
        }
    }
}
