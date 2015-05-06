package controllers;

import java.util.Map;
import java.util.List;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

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
}
