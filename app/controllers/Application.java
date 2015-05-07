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
import models.*;

import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;


public class Application extends Controller {
    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result emailtest() {
        Email email = new Email();

        email.setSubject("Simple email");
        email.setFrom("Mister FROM <ethics@lysdev.com>");
        email.addTo("Miss TO <me@mause.me>");

        // email.setBodyText("A text message");
        email.setBodyHtml(views.html.index.render().body());

        MailerPlugin.send(email);

        return ok("Sent " + email);
    }

    public static String get_section_link(Long id, Integer num) {
        return String.format(
            "/application/%d/section%d",
            id,
            num
        );
    }

    public static String slugify(String raw) {
        return raw.toLowerCase().replace("/", "_").replace(" ", "_");
    }
}
