package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.Expr;


@Entity
public class UserModel extends Model {
    public static final long serialVersionUID = 0;

    @Id
    @Constraints.Min(10)
    public String curtin_id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String encrypted_password;

    public static Finder<String,UserModel> find = new Finder<String,UserModel>(
        String.class, UserModel.class
    );

    public static UserModel authenticate(String curtin_id, String password) {
        if (curtin_id == null || password == null) return null;

        HashMap<String,Object> props = new HashMap<String,Object>();
        props.put("curtin_id", curtin_id);
        props.put("encrypted_password", password);

        com.avaje.ebean.Query<UserModel> query = UserModel.find.where(Expr.allEq(props));

        System.out.println(query.getGeneratedSql());

        query.getFirstRow();
        List<UserModel> lst = query.findList();

        if (lst == null || lst.size() == 0) {
            return null;
        } else {
            return lst.get(0);
        }
    }
}
