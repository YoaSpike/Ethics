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

    @BodyParser.Of(BodyParser.Json.class)
    public static Result sayHello() {
        JsonNode json = request().body().asJson();

        String name = json.findPath("name").textValue();
        if (name == null) return badRequest("Missing parameter [name]");

        return ok("Hello " + name);
    }
}
