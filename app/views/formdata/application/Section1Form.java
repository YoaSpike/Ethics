package views.formdata.application;

import java.util.List;

import javax.validation.*;
import play.data.validation.Constraints.*;

public class Section1Form {
    @Valid
    public String project_title;

    @Valid
    public ProjectType project_type;

    @Valid
    public String background;

    @Valid
    public String aims_and_hypothesis;

    @Valid
    public String methods;

    @Valid
    public Person pi;

    @Valid
    public List<Person> coinvestigators;

    @Valid
    public Person contact_person;

    public static class Person {
        // not all of these are entered for every single use
        @Valid
        public String name;
        @Valid
        public String area;
        @Valid
        public String id;
        @Valid
        public Role role;
        @Valid
        public Boolean candidacy_approved;
        @Valid
        public Boolean research_integrity; // aka SOL

        @Valid
        public String telephone;
        @Valid
        @Email
        public String email;
    }
}
