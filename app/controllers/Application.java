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

    @BodyParser.Of(BodyParser.Json.class)
    public static Result sayHello() {
        JsonNode json = request().body().asJson();

        String name = json.findPath("name").textValue();
        if (name == null) return badRequest("Missing parameter [name]");

        return ok("Hello " + name);
    }

    public static Result emailtest() {
        Email email = new Email();

        email.setSubject("Simple email");
        email.setFrom("Mister FROM <ethics@lysdev.com>");
        email.addTo("Miss TO <me@mause.me>");

        email.setBodyText("A text message");
        // email.setBodyHTML(views.html.index.render()); or whatever

        MailerPlugin.send(email);

        return ok("Sent " + email);
    }

    public static Result demo(String demo_name) {
        if (demo_name.equals("section1")) {
            return ok(views.html.demos.section1.render());

        } else if (demo_name.equals("section6")) {
            return ok(views.html.demos.section6.render(
                form(views.formdata.Section6Form.class)
            ));

        } else if (demo_name.length() > 7 && demo_name.substring(0, 7).equals("section")) {
            String section_num = demo_name.substring(7);

            return ok(views.html.demos.unimplemented.render(
                Integer.parseInt(section_num)
            ));

        } else {
            return badRequest("Bad in general");
        }
    }

    public static String get_section_link(Integer num) {
        return "/demos/" + (num == 0 ? "ethics_triage" : "section" + num);
    }
}
