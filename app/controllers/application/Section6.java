package controllers.application;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.common.io.ByteStreams;

import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;
import play.mvc.Http.MultipartFormData.*;

import views.formdata.*;


public class Section6 extends Controller {
    private static final Form<Section6Form> section6Form = form(Section6Form.class);

    public static Result section6(Long id) {
        int section_num = 6;
        return ok(views.html.application.section6.render(id, section_num, section6Form));
    }

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

            // Pseudocode follows
            /*
            // first we find the section6 record we need to attach this attachment to
            List<Section6Model> models = (
                Section6Model.find
                .where()
                    .eq("application.id", id)
                .query()
                .findList()
            );

            if (models.size() == 0) {
                throw new Error("bugger");
            }

            Section6AttachmentModel new_model = new Section6AttachmentModel();
            new_model.type = attach.type;
            new_model.version = attach.version;
            new_model.date = attach.date;
            new_model.mimetype = fp.getContentType();
            new_model.filename = fp.getFilename();
            new_model.data = bdata;
            new_model.section6 = models.get(0); // oh goody

            Ebean.save(new_model); // hopefully this works
            */
        }
    }
}
