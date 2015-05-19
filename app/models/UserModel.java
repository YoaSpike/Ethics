package models;

import java.util.*;
import javax.persistence.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import org.mindrot.jbcrypt.BCrypt;


@Entity
@Table(name="UserModel")
public class UserModel extends Model {
    public static final long serialVersionUID = 0;

    public UserModel(String id) { 
        this(Integer.parseInt(id)); 
    }    

    public UserModel(int    id) { 
        this.id = id;
         }

    public UserModel(){
        super(); 
        }

    public String toString() {
        return String.format(
            "<User %d %s %s>",
			/*Testing - added this.id & %d*/
			this.id,
            this.curtin_id,
            this.name
        );
    }
    /*Primary key id here with applicationModel id*/
    @Id
    public Integer id;

    @OneToMany
    public List<ApplicationModel> applications;

    @Constraints.Required
    public String curtin_id;

    @Constraints.Required
    public String name;

    @Constraints.Email
    public String email;

    @Constraints.Required
    public String hashed_password;
	
    public static Finder<String,UserModel> find = new Finder<String,UserModel>(
        String.class, UserModel.class
    );

    public static UserModel authenticate(String email, String password) {
        HashMap<String,Object> props = new HashMap<String,Object>();

        List<UserModel> lst = (
            UserModel.find
            .where()
                .eq("email", email)
            .query()
            .setMaxRows(1)
            .findList()
        );

        if (lst == null || lst.size() == 0) {
            Logger.info("No such user as {}", email);
            return null;

        } else {
            UserModel found = lst.get(0);
            String hashed_password = found.hashed_password;

            if (BCrypt.checkpw(password, hashed_password)) {
                return found;

            } else {
                Logger.info("Invalid password for {}", email);

                return null;
            }
        }
    }
}