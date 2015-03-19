package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import business.EthicsFormState;


@Entity
public class EthicsFormModel extends Model {
    public static final long serialVersionUID = 0;

    @Id
    @Constraints.Min(10)
    public int id;

    @Constraints.Required
    public EthicsFormState state = EthicsFormState.DRAFT;

    // @Constraints.Required
    // public String name;

    public static Finder<String,EthicsFormModel> find = new Finder<String,EthicsFormModel>(
        String.class, EthicsFormModel.class
    );

    public void transitionTo(String stateName) {
        throw new Error();
    }
}
