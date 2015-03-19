package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;
import com.fasterxml.jackson.databind.JsonNode;

import views.html.*;
import views.formdata.*;


public class Application extends Controller {
    public static Result index() {
        return ok(index.render());
    }

    public static Result ethics_triage() {
        Form<EthicsTriageForm> triageForm = form(EthicsTriageForm.class);
        return ok(ethics_triage.render(triageForm));
    }

    public static Result ethics_triage_post() {
        Form<EthicsTriageForm> triageForm = form(EthicsTriageForm.class);

        EthicsTriageForm data = triageForm.bindFromRequest().get();

        System.out.println("justification: " + data.justification);
        System.out.println("illegal_activities: " + data.illegal_activities);

        return ethics_triage();
    }

    public static Result general_information() {
        return ok(general_information.render());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result sayHello() {
        JsonNode json = request().body().asJson();

        String name = json.findPath("name").textValue();
        if (name == null) return badRequest("Missing parameter [name]");

        return ok("Hello " + name);
    }
}
