package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import com.google.common.io.ByteStreams;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.html.*;
import views.html.forms.*;
import views.formdata.*;
import models.*;


public class EthicsApplication extends Controller {
    public static Result application_summary(Long id) {
        return ok("Summararily");
    }

    public static Result ethics_triage(Long id) {
        return ok("Triage those ethics! for application with id " + id);
    }

    public static Result section1(Long id) {
        return ok("Section1 with id " + id);
    }

    public static Result section2(Long id) {
        return ok("Section2 with id " + id);
    }
}
