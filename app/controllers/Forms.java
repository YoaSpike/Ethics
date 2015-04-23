package controllers;

import java.util.Map;
import java.util.List;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;
// import com.fasterxml.jackson.databind.JsonNode;

import views.html.forms.*;
import views.formdata.*;
import models.*;


public class Forms extends Controller {
    public static Result index() {
        List<EthicsFormModel> things = (
            EthicsFormModel.find
            .where()
                .eq("owner", controllers.Accounts.getCurrentUser())
            .query()
            .findList()
        );

        return ok(forms_index.render(things));
    }

    public static Result viewall() {
        List<EthicsFormModel> forms = (
            EthicsFormModel.find
            .where()
                .eq("owner", controllers.Accounts.getCurrentUser())
            .query()
            .findList()
        );

        return ok(views.html.viewall.render(forms));
    }

    public static Result display_form_progress(Long id) {
        return ok(id.toString());
    }

    public static Result ethics_triage() {
        return ethics_triage(null);
    }

    public static Result ethics_triage(EthicsTriageForm data) {
        Form<EthicsTriageForm> triageForm = form(EthicsTriageForm.class);

        if (data != null) {
            triageForm.fill(data);
            // re-fill with data for editing...
        } else {
            // here we would retrieve a filled form from the database, if available
        }

        return ok(views.html.forms.ethics_triage.render(triageForm));
    }

    public static Result ethics_triage_post() {
        if (request().body().isMaxSizeExceeded()) {
            return badRequest("Too much data!");
        }

        Form<EthicsTriageForm> triageForm = form(EthicsTriageForm.class).bindFromRequest();

        if (triageForm.hasErrors()) {
            System.out.println(triageForm.errorsAsJson());
            return badRequest(views.html.forms.ethics_triage.render(triageForm));
        }

        EthicsTriageForm data = triageForm.get();
        System.out.println(data);

        return ethics_triage(data);
    }

    public static Result general_information() {
        Form<InfoForm> infoForm = form(InfoForm.class);

        return ok(views.html.forms.general_information.render(infoForm));
    }

    public static Result general_information_post() {
        Form<InfoForm> infoForm = form(InfoForm.class);

        System.out.println(request().body().asRaw());

        // Map<String,String[]> formatted = request().body().asFormUrlEncoded();
        // Map<String,List<String>> reformatted = new HashMap<String,List<String>>();

        // for (Map.Entry<String,String[]> entry : formatted.entrySet()) {
        //     reformatted.put(
        //         entry.getKey(),
        //         new ArrayList<String>(Arrays.asList(entry.getValue()))
        //     );
        // }

        // System.out.println(reformatted);

        return ok(views.html.forms.general_information.render(infoForm));
    }

    public static Result themes_in_research_ethics() {
        Form<ThemesForm> themesForm = form(ThemesForm.class);
        return ok(themes_in_research_ethics.render(themesForm));
    }

    public static Result themes_in_research_ethics_post() {
        return badRequest();
    }
}
