package ma.ensias.miniprojet.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import ma.ensias.miniprojet.Model.User;
import ma.ensias.miniprojet.Service.UserService;

import java.io.Serializable;
import java.util.List;

@Named
//@SessionScoped
@ViewScoped
@Data

public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;
    private User selectedUser;

    private User newUser = new User();  // Initialize the newUser property
    private List<User> userList;

    @PostConstruct
    public void init() {
        userList = userService.getAllUsers();
    }


    // delete user
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }

    public void editUser() {
        userService.updateUser(selectedUser);
        // Refresh the user list after editing
        userList = userService.getAllUsers();

    }


    // get all users
    public List<User> getUserList() {
        return userService.getAllUsers();
    }

    // get logger user
    public Object getLoggedUser() {
        return userService.getLoggedUser();
    }

    // add user method
    public String addUser(User user) {
        try {
            userService.addUser(user); // Add the user to the database
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Utilisateur ajouté avec succès !"));
            return "admin.xhtml?faces-redirect=true"; // Redirect to admin.xhtml
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur lors de l'ajout de l'utilisateur."));
            return null; // Stay on the same page if there's an error
        }
    }
}