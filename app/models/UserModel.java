package models;

import java.util.*;
import javax.persistence.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.Expr;

import org.mindrot.jbcrypt.BCrypt;


@Entity
public class UserModel extends Model {
    public static final long serialVersionUID = 0;

    // for
    public UserModel(String curtin_id) {
        this.curtin_id = curtin_id;
    }
    public UserModel() { super(); }

    // copy constructor, required for fixtures
    public UserModel(UserModel other) {
        super();
        this.curtin_id = other.curtin_id;
        this.name = other.name;
        this.hashed_password = other.hashed_password;
        System.out.println(this);
    }

    @Id
    @Constraints.Required
    public String curtin_id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String hashed_password;

    public static Finder<String,UserModel> find = new Finder<String,UserModel>(
        String.class, UserModel.class
    );

    public static UserModel authenticate(String curtin_id, String password) {
        HashMap<String,Object> props = new HashMap<String,Object>();

        List<UserModel> lst = (
            UserModel.find
            .where()
                .eq("curtin_id", curtin_id)
            .query()
            .setMaxRows(1)
            .findList()
        );

        if (lst == null || lst.size() == 0) {
            Logger.info("No such user as {}", curtin_id);
            return null;

        } else {
            UserModel found = lst.get(0);
            String hashed_password = found.hashed_password;

            if (BCrypt.checkpw(password, hashed_password)) {
                return found;

            } else {
                Logger.info("Invalid password for {}", curtin_id);

                return null;
            }
        }
    }
}
