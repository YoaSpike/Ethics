package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;

import views.html.*;
import views.formdata.*;


public class Accounts extends Controller {
    public static Result login() {
        return ok(login.render(form(LoginForm.class)));
    }

    public static Result login_post() {
        Form<LoginForm> loginForm = form(LoginForm.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            session().clear();
            session("username", loginForm.get().username);
            return redirect(routes.Application.index());
        } else {
            return ok(login.render(loginForm));
        }
    }

    public static Result logout() {
        session().clear();
        return redirect(routes.Application.index());
    }

    public static Result user_list() {
        return ok(user_list.render(
            models.UserModel.find.all()
        ));
    }

    // @BodyParser.Of(BodyParser.Json.class)
    public static Result add_user() {
        String curtin_id, name;

        System.out.println(request());
        System.out.println(request().body());
        System.out.println(request().body().asText());
        System.out.println(request().body().asJson());
        // System.out.println(request().body().asString());

        JsonNode json = request().body().asJson(), curtin_id_j, name_j;
        if (json == null) return badRequest("Json not supplied as required");

        curtin_id_j = json.findPath("curtin_id");
        curtin_id = curtin_id_j.textValue();

        name_j = json.findPath("name");
        name = name_j.textValue();

        if (curtin_id == null) return badRequest("Missing parameter [curtin_id]");
        if (name == null) return badRequest("Missing parameter [name]");

        models.UserModel mod = new models.UserModel();
        mod.name = name;
        mod.curtin_id = curtin_id;

        Ebean.save(mod);

        return ok("Saved to db");
    }
}
