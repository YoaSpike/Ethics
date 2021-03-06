package views.formdata;

import play.data.validation.*;

public class LoginForm {
    public LoginForm() {}

    // fields
    @Constraints.Required
    @Constraints.Email
    public String email;
    public void setEmail(String val) { this.email = val; }
    public String getEmail() {  return this.email; }

    @Constraints.Required
    public String password;
    public void setPassword(String val) { this.password = val; }
    public String getPassword() {  return this.password; }

    // methods
    public String toString() {
        return String.format("<LoginForm \"%s\" \"%s\">", this.email, this.password);
    }

    public String validate() {
        if (models.UserModel.authenticate(this.email, this.password) == null) {
              return "Invalid email or password";
        }
        return null; // no error
    }
}
