package views.formdata.application;

import javax.validation.*;
import play.data.validation.*;

public class Section3Form {
    // question 13
    @Valid
    public Boolean is_clinical;

        @Valid
        public Boolean placebo_non_treatment_group_used;
        @Valid
        public String placebo_non_treatment_group_used_description;

        @Valid
        public Boolean trial_is_registered;
        @Valid
        public String trial_is_registered_description;

        @Valid
        public Boolean sufficiency_for_safe_trial;
        @Valid
        public String sufficiency_for_safe_trial_description;

        @Valid
        public Boolean clear_participant_information_statement;
        @Valid
        public String clear_participant_information_statement_description;

    // question 14
    @Valid
    public Boolean health_information_use;
    @Valid
    public String health_information_use_description;

    // question 15
    @Valid
    public Boolean human_genetics;
    @Valid
    public String human_genetics_description;
}
