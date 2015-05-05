package views.formdata;

import java.util.List;
import javax.validation.*;

import play.data.validation.Constraints.*;

import org.joda.time.DateTime;


enum AttachmentType {
    PROTOCOL_RESEARCH_PROPOSAL,
    RECRUITMENT_MATERIAL,
    PARTICIPANT_INFORMATION_STATEMENT_AND_CONSENT_FORMS,
    PARENT_INFORMATION_STATEMENT_AND_CONSENT_FORMS,
    CHILD_INFORMATION_STATEMENT_AND_ASSENT_FORMS,
    QUESTIONAIRES_SURVEY_INSTRUMENTS,
    TRANSLATIONS_WHERE_LANGUAGES_OTHER_THAN_ENGLISH_ARE_USED,
    RECRUITMENT_MATERIALS,
    INVESTIGATOR_BROCHURE_OR_PRODUCT_INFORMATION,
    WORKING_WITH_CHILDRENS_CARD,
    SOL;
}


public class Section6Form {
    @Valid
    @Required
    public List<Attachment> attachments;
    public List<Attachment> getAttachments() { return this.attachments; }
    public void setAttachments(List<Attachment> val) { this.attachments = val; }

    public static class Attachment {
        @Required
        @Valid
        public int type; // to be re-interpreted later

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
