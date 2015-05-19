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

	//Needed to conbine inherited classes
	/*@Column(name="Important_ID")*/
	/*@OneToOne(mappedBy = "ApplicationModel")*/
	@Id
	@Valid
	public int id;
	
	@OneToMany(optional=false, cascade=CascadeType.ALL,
		 mappedBy="applicationModel", targetEntity=UserModel.class)
	public UserModel userModel;
	
    /*@OneToOne(optional=false)
    @JoinColumn(name="curtinId")*/
    public Section3 section3;
    public Section4 section4;

	public String toString() {
        return String.format(
            "<User %s>",
			//Testing - added this.id & %d
			this.id
        );
	}
	
};
