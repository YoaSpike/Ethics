package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Section6")
public class Section6Model extends Model{

	public Section6Model(){
		super();
	}

	public Section6Model(int id){
		super();
		this.id = id;
	}

	@Id
	@Valid
	public int id;
	
	public static Finder<Integer,Section6Model> find = new Finder<Integer,Section6Model>(
        Integer.class, Section6Model.class
    );
	
	// None of these fields are strictly necessary unless attachments are valid. Does that mean we don't need @Constraints.required?
	
	// For all following boolean fields, null represents N/A and is hence the default.
	
	public boolean researchProposalAttached = null;
	public int researchProposalVersion;
	public Date researchProposalDate;
	
	public boolean reruitmentMaterialAttached = null;
	public int recruitmentMaterialVersion;
	public Date recruitmentMaterialDate;
	
	public boolean participantInformationAttached = null;
	public int participantInformationVersion;
	public Date participantInformationDate;
	
	public boolean parentInformationAttached = null;
	public int parentInformationVersion;
	public Date parentInformationDate;
	
	public boolean childInformationAttached = null;
	public int childInformationVersion;
	public Date childInformationDate;
	
	public boolean[] questionnairesInstrumentsAttached = new bool[4]; // There are up to four entries in the form for questionnaires/survey instruments
	for (int i=0; i<4; i++)
	{
		questionnairesInstrumentsAttached[i] = null;
	}
	public int[] questionnairesInstrumentsVersions = new int[4];
	public Date[] questionnairesInstrumentsDates = new Date[4]; // Don't need to initialise, only allocate. Default Date constructor is to the current time, which makes no sense anyway.
	
	public boolean translationsAttached = null;
	public int translationsVersion;
	public Date translationsDate;
	
	public boolean[] recruitmentMaterialsAttached = new bool[4]; // I don't know why this is separate from recruitmentMaterial
	for (int i=0; i<4; i++)
	{
		recruitmentMaterialsAttached[i] = null;
	}
	public int recruitmentMaterialsVersions = new int[4];
	public Date recruitmentMaterialsDates = new Date[4];
	
	public boolean productInformationAttached = null;
	public int productInformationVersion;
	public Date productInformationDate;
	
	public boolean workingWithChildrenAttached = null;
	public int workingWithChildrenVersion;
	public Date workingWithChildrenDate;
	
	// The full term is 'SOL Research Integrity Professional Development Program training certificate', but let's be reasonable.
	public boolean integrityCertAttached = null;
	public int integrityCertVersion;
	public Date integrityCertDate;
	
}