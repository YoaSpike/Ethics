package business;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

public class Secured extends Security.Authenticator {
    @Override
    public String getUsername(Context ctx) {
        if (controllers.Accounts.getCurrentUser() != null) {
            return ctx.session().get("email");
        } else {
            return null;
        }
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(controllers.routes.Accounts.login(ctx.request().path()));
    }
}
