package views.formdata;

public class LoginForm {
    public String username;
    public String encrypted_password;

    public String validate() {
        if (models.UserModel.authenticate(this.username, this.encrypted_password) == null) {
              return "Invalid username or password";
        }
        return null;
    }
}

