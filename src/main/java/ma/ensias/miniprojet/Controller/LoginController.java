package ma.ensias.miniprojet.Controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import ma.ensias.miniprojet.Exceptions.UserNotFoundException;
import ma.ensias.miniprojet.Model.User;
import ma.ensias.miniprojet.Service.UserService;

import java.io.Serializable;

@Named
@SessionScoped
@Data

public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    @Inject
    private UserService userService;

    public String login() {
        User mUser = null;
        try {
            mUser = userService.authenticate(username, password);
            if (mUser != null) {
                // Store the logged-in user in the session
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", mUser);

                // Redirect based on user role
                if (mUser.getRole().equals("ADMIN")) {
                    return "admin.xhtml?faces-redirect=true";
                } else if (mUser.getRole().equals("USER")) {
                    return "user.xhtml?faces-redirect=true";
                }
            }
        } catch (UserNotFoundException e) {
            return "error.xhtml?faces-redirect=true";
        }
        return "login.xhtml?faces-redirect=true"; // Redirect back to login if authentication fails
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

}