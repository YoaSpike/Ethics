package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import business.EthicsFormState;


@Entity
public class EthicsTriageModel extends Model {
    public static final long serialVersionUID = 0;

    public EthicsTriageModel(String id) { this(Integer.parseInt(id)); }
    public EthicsTriageModel(int id)    { this.id = id; }
    public EthicsTriageModel(EthicsTriageModel other) {
        this.id = other.id;
        this.form = other.form;
        this.interventions_and_therapies = other.interventions_and_therapies;
        this.human_genetics = other.human_genetics;
        this.pregnant_women_or_fetuses = other.pregnant_women_or_fetuses;
        this.medically_dependant = other.medically_dependant;
        this.cognitively_impaired = other.cognitively_impaired;
        this.aboriginal_or_torres_strait_islander = other.aboriginal_or_torres_strait_islander;
        this.illegal_activities = other.illegal_activities;
        this.justification = other.justification;
    }
    public String toString() {
        String s_form = (
            this.form == null ?
            "no form" : this.form.toString()
        );

        return String.format(
            "<EthicsTriageModel %d attached to %s>",
            this.id, s_form
        );
    }

    @Id
    @Constraints.Required
    public int id;

    @OneToOne(optional=false)
    public EthicsFormModel form = null;

    @Constraints.Required
    public boolean interventions_and_therapies = false;
    @Constraints.Required
    public boolean human_genetics = false;
    @Constraints.Required
    public boolean pregnant_women_or_fetuses = false;
    @Constraints.Required
    public boolean medically_dependant = false;
    @Constraints.Required
    public boolean cognitively_impaired = false;
    @Constraints.Required
    public boolean aboriginal_or_torres_strait_islander = false;
    @Constraints.Required
    public boolean illegal_activities = false;
    public String justification = null;

    public static Finder<String,EthicsTriageModel> find = new Finder<String,EthicsTriageModel>(
        String.class, EthicsTriageModel.class
    );
}
