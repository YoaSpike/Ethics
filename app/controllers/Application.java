package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import views.html.*;
import views.html.forms.*;
import views.formdata.*;


public class Application extends Controller {
    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result ethics_triage() {
        Form<EthicsTriageForm> triageForm = form(EthicsTriageForm.class);
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

        // re-fill with data for editing...
        triageForm = form(EthicsTriageForm.class).fill(data);

        // render the data back the in form for future editing
        return ok(views.html.forms.ethics_triage.render(triageForm));
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

    @BodyParser.Of(BodyParser.Json.class)
    public static Result sayHello() {
        JsonNode json = request().body().asJson();

        String name = json.findPath("name").textValue();
        if (name == null) return badRequest("Missing parameter [name]");

        return ok("Hello " + name);
    }

    public static Result themes_in_research_ethics() {
        Form<ThemesForm> themesForm = form(ThemesForm.class);
        return ok(themes_in_research_ethics.render(themesForm));
    }

    public static Result themes_in_research_ethics_post() {
        return badRequest();
    }
}
