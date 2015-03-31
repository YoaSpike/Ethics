package views.formdata;

import play.data.validation.*;

public class LoginForm {
    public LoginForm() {}

    // fields
    @Constraints.Required
    @Constraints.Pattern(value="^[0-9]+[A-Za-z]?$", message="A valid Curtin ID is required")
    public String curtin_id;
    public void setCurtin_id(String val) { this.curtin_id = val; }
    public String getCurtin_id() {  return this.curtin_id; }

    @Constraints.Required
    public String encrypted_password;
    public void setEncrypted_password(String val) { this.encrypted_password = val; }
    public String getEncrypted_password() {  return this.encrypted_password; }

    // methods
    public String toString() {
        return "<LoginForm \"" + curtin_id + "\" \"" + encrypted_password + "\">";
    }

    public String validate() {
        if (models.UserModel.authenticate(this.curtin_id, this.encrypted_password) == null) {
              return "Invalid Curtin ID or password";
        }
        return null; // no error
    }
}
