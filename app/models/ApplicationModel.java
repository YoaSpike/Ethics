package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="ApplicationModel")
public class ApplicationModel extends Model{
	
	public ApplicationModel(){
		super();
	}

	public ApplicationModel(int id){
		super();
		this.id = id;
	}

	@Id
	@Valid
	public int id;
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	public UserModel userModel;
	
	@OneToOne
	public Section3Model section3;

	@OneToOne
    public Section4Model section4;

	public String toString() {
        return String.format(
            "<User %s>",
			//Testing - added this.id & %d
			this.id
        );
	}
	
};