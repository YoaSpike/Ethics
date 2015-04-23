package controllers;

import play.*;
import play.mvc.*;
import play.Logger;
import play.data.Form;
import play.data.validation.*;
import static play.data.Form.*;

import org.mindrot.jbcrypt.BCrypt;

import views.html.*;
import views.formdata.*;
import models.*;


public class Accounts extends Controller {
    public static UserModel getCurrentUser() {
        if (isLoggedIn()) {
            return (
                UserModel.find
                .where()
                    .eq("email", getEmail())
                .query()
                .findList()
                .get(0)
            );
        } else {
            return null;
        }
    }

    public static String getEmail() {
        return session().get("email");
    }

    public static boolean isLoggedIn() {
        return getEmail() != null;
    }

    public static Result login() {
        if (isLoggedIn()) {
            return redirect(routes.Application.index());
        } else {
            return ok(login.render(form(LoginForm.class)));
        }
    }

    public static Result login_post() {
        if (request().body().isMaxSizeExceeded()) {
            return badRequest("Too much data!");
        }

        Form<LoginForm> loginForm = form(LoginForm.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            Logger.debug("Login failed");
            return badRequest(login.render(loginForm));

        } else {
            LoginForm data = loginForm.get();
            Logger.debug("Login succeeded for " + data.email);

            session().clear();
            session("email", data.email.toString());

            return redirect(routes.Application.index());
        }
    }

    public static Result logout() {
        Logger.debug("Logged out of " + getEmail());

        session().clear();
        return redirect(routes.Application.index());
    }

    public static Result user_list() {
        return ok(user_list.render(
            models.UserModel.find.all()
        ));
    }

    // this is really just for debugging purposes :P
    // there is a nice simple python script in the root directory you can to add
    // users via this route
    public static class AddUserForm {
        @Constraints.Required
        public String email;

        @Constraints.Required
        public String password;
    }

    public static Result add_user() {
        Form<AddUserForm> loginForm = form(AddUserForm.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(loginForm.errorsAsJson());

        } else {
            models.UserModel mod = new models.UserModel();
            AddUserForm data = loginForm.get();
            mod.email = data.email;

            String password = data.password;

            mod.hashed_password = BCrypt.hashpw(
                data.password,
                BCrypt.gensalt()
            );

            mod.save();

            return ok("Saved to db");
        }
    }
}
