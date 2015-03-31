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

    public EthicsFormModel(String id) { this(Integer.parseInt(id)); }
    public EthicsFormModel(int id)    { this.id = id; }
    public EthicsFormModel()          { super(); }
    public EthicsFormModel(EthicsFormModel other) {
        super();
        this.id = other.id;
        this.owner = other.owner;
        this.state = other.state;
        this.ethicsTriage = other.ethicsTriage;
    }

    @Id
    @Constraints.Required
    public int id;

    @ManyToOne()
    public UserModel owner;

    @Constraints.Required
    public EthicsFormState state = EthicsFormState.DRAFT;

    @Constraints.Required
    @OneToOne(mappedBy="form", optional=true)
    public EthicsTriageModel ethicsTriage = null;

    public static Finder<Integer,EthicsFormModel> find = new Finder<Integer,EthicsFormModel>(
        Integer.class, EthicsFormModel.class
    );

    public void transitionTo(String stateName) {
        throw new Error();
    }
}
