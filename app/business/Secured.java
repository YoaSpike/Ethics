package business;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

public class Secured extends Security.Authenticator {
    @Override
    public String getUsername(Context ctx) {
        // we don't need to manually validate the session, in theory,
        // because play signs and validates session variables
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(controllers.routes.Accounts.login(ctx.request().path()));
    }
}
