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

    public static Result section6(Long id) {
        return ok(views.html.section6.render(
            form(Section6Form.class)
        ));
    }

    private static final Form<Section6Form> section6Form = form(Section6Form.class);

    private static class ReworkedMultipart {
        public Map<String,String[]> form_data;
        public Map<String,Http.MultipartFormData.FilePart> files;
    }
    private static ReworkedMultipart remap_multipart(Http.MultipartFormData multipart) {
        ReworkedMultipart res = new ReworkedMultipart();

        res.form_data = new HashMap<String,String[]>(multipart.asFormUrlEncoded());
        res.files = new HashMap<String,Http.MultipartFormData.FilePart>();

        for (Http.MultipartFormData.FilePart file : multipart.getFiles()) {
            res.form_data.put(file.getKey(), new String[] { file.getFilename() });
            res.files    .put(file.getKey(), file);
        }

        return res;
    }

    public static Result section6_post(Long id) {
        // if we want a file in a form, we need to do extra work

        ReworkedMultipart multipart = remap_multipart(request().body().asMultipartFormData());
        Form<Section6Form> filledForm = section6Form.bindFromRequest(multipart.form_data);

        if(filledForm.hasErrors()) {
            return badRequest(filledForm.errorsAsJson());

        } else {
            Section6Form data = filledForm.get();

            saveAttachmentsToApplication(
                data.attachments,
                multipart.files,
                id
            );

            return ok(data.toString());
        }
    }

    public static void saveAttachmentsToApplication(List<Section6Form.Attachment> attachments, Map<String,Http.MultipartFormData.FilePart> files, Long id) {

        play.Logger.info(attachments.size() + " attachments");
        for (Section6Form.Attachment attach : attachments) {
            String key = String.format("attachments[%d].file", attach.idx);
            Http.MultipartFormData.FilePart fp = files.get(key);

            byte[] bdata = null;
            try {
                bdata = ByteStreams.toByteArray(new FileInputStream(fp.getFile()));
            } catch (IOException ioe) {}

            play.Logger.info(String.format(
                "Type: %s, version: %s, date: %s, file: %s %d bytes long",
                attach.type,
                attach.version,
                attach.date,
                attach.file,
                bdata.length
            ));
        }
    }
}
