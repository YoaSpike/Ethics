package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Section2")
public class Section2Model extends Model{

	public Section2Model(){
		super();
	}

	public Section2Model(int id){
		super();
		this.id = id;
	}

	@Id
	@Valid
	public int id;

	@OneToOne(optional=false, cascade=CascadeType.ALL,
		 mappedBy="section2", targetEntity=ApplicationModel.class)
	public ApplicationModel applicationModel;

	public static Finder<Integer,Section2Model> find = new Finder<Integer,Section2Model>(
        Integer.class, Section2Model.class
    );

    @Constraints.Required
    public String potentialHarmOrRisk7Text = null;

   	@Constraints.Required
    public String riskManagementStrategy = null;

    @Constraints.Required
    public boolean willParticipantsBeGiveFinancial9 = false;
    public String willParticipantsBeGiveFinancial9Text = null;

    public boolean databaseMedicalRecords10a = false;
    public String databaseMedicalRecords10aText = null;

    public boolean snowballRecruitment10b = false;
    public String snowballRecruitment10b10bText = null;

    public boolean socialMedia10c = false;
    public String socialMedia10cText = null;

    public boolean printMedia10d = false;
    public String printMedia10dText = null;

    public boolean classroomOrHospitalGroup10e = false;
    public String classroomOrHospitalGroup10eText = null;

    public boolean radioTelevision10f = false;
    public String radioTelevision10fText = null;

    public boolean other10g = false;
    public String other10gText = null;

    @Constraints.Required
    public boolean willParticipantsProvideConsent11 = false;
    public String willParticipantsProvideConsent11Text = null;

    @Constraints.Required
    public boolean doesTheResearchUseDeception12 = false;
    public String doesTheResearchUseDeception12Text = null;
}
