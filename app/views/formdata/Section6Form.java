package views.formdata;

import java.util.List;
import javax.validation.*;

import play.data.validation.Constraints.*;

import org.joda.time.DateTime;


public class Section6Form {
    @Valid
    @Required
    public List<Attachment> attachments;
    public List<Attachment> getAttachments() { return this.attachments; }
    public void setAttachments(List<Attachment> val) { this.attachments = val; }

    public static class Attachment {
        @Required
        @Valid
        public AttachmentType type;

        @Required
        @Valid
        public String version;

        @Required
        @Valid
        public DateTime date;

        @Valid
        public int idx; // we need to know so we can match up the file data later
    }
}
