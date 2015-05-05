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
import play.mvc.Http.MultipartFormData.*;
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

    public static Result section6(Long id) {
        return ok(views.html.application.section6.render(
            form(Section6Form.class)
        ));
    }

    private static final Form<Section6Form> section6Form = form(Section6Form.class);

    public static Result section6_post(Long id) {
        // if we want a file in a form, we need to do extra work

        Form<Section6Form> filledForm = section6Form.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest(filledForm.errorsAsJson());

        } else {
            Section6Form data = filledForm.get();

            play.Logger.info("data: " + data);
            play.Logger.info("data.attachments: " + data.attachments);

            Map<String,FilePart> files = new HashMap<String,FilePart>();
            for (FilePart file : request().body().asMultipartFormData().getFiles()) {
                files.put(file.getKey(), file);
            }

            saveAttachmentsToApplication(
                data.attachments,
                files,
                id
            );

            return ok(data.toString());
        }
    }

    public static void saveAttachmentsToApplication(List<Section6Form.Attachment> attachments, Map<String,FilePart> files, Long id) {

        play.Logger.info(attachments.size() + " attachments");
        for (Section6Form.Attachment attach : attachments) {
            String key = String.format("attachments[%d].file", attach.idx);
            FilePart fp = files.get(key);

            byte[] bdata = null;
            try {
                bdata = ByteStreams.toByteArray(new FileInputStream(fp.getFile()));
            } catch (IOException ioe) {}

            play.Logger.info(String.format(
                "Type: %s, version: %s, date: %s, file: %s %d bytes long",
                attach.type,
                attach.version,
                attach.date,
                fp.getFilename(),
                bdata.length
            ));
        }
    }
}
