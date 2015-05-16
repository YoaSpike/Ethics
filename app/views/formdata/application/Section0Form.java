package views.formdata.application;

import javax.validation.*;
import play.data.validation.Constraints.*;

// POJOs!
public class Section0Form {
    @Valid
    public boolean interventions_and_therapies = false;
    @Valid
    public boolean human_genetics = false;
    @Valid
    public boolean pregnant_women_or_fetuses = false;
    @Valid
    public boolean medically_dependant = false;
    @Valid
    public boolean cognitively_impaired = false;
    @Valid
    public boolean aboriginal_or_torres_strait_islander = false;
    @Valid
    public boolean illegal_activities = false;
    public String justification = null;

    private int ib(boolean val) {
        return val ? 1 : 0;
    }

    public String toString() {
        return String.format(
            "<Section0Form %d %d %d %d %d %d %d \"%s\">",
            ib(interventions_and_therapies),
            ib(human_genetics),
            ib(pregnant_women_or_fetuses),
            ib(medically_dependant),
            ib(cognitively_impaired),
            ib(aboriginal_or_torres_strait_islander),
            ib(illegal_activities),
            justification
        );
    }

    public boolean anyChecked() {
        return (
            this.interventions_and_therapies ||
            this.human_genetics ||
            this.pregnant_women_or_fetuses ||
            this.medically_dependant ||
            this.cognitively_impaired ||
            this.aboriginal_or_torres_strait_islander ||
            this.illegal_activities
        );
    }

    public String validate() {
        boolean valid_justification = (
            this.justification != null && this.justification != ""
        );

        if (valid_justification && !this.anyChecked()) {
            return "Justification should only be provided if one of the above boxes is checked";
        } else {
            return null;
        }
    }
}
