package controllers.application;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.formdata.*;


public class Section0 extends Controller {
    private static final Form<EthicsTriageForm> triageForm = form(EthicsTriageForm.class);

    public static Result section0(Long id) {
        return section0((EthicsTriageForm) null);
    }

    public static Result section0(EthicsTriageForm data) {
        Form<EthicsTriageForm> filledTriageForm = triageForm;

        if (data != null) {
            filledTriageForm = filledTriageForm.fill(data);
            // re-fill with data for editing...
        } else {
            // here we would retrieve a filled form from the database, if available
        }

        return ok(views.html.application.section0.render(filledTriageForm));
    }

    public static Result section0_post(Long id) {
        if (request().body().isMaxSizeExceeded()) {
            return badRequest("Too much data!");
        }

        Form<EthicsTriageForm> filledForm = triageForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            System.out.println(triageForm.errorsAsJson());
            return badRequest(views.html.application.section0.render(triageForm));

        } else {
            EthicsTriageForm data = filledForm.get();
            System.out.println(data);

            return section0(data);
        }
    }
}
