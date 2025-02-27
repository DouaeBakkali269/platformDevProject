package ma.ensias.miniprojet.Service;

import jakarta.ejb.Stateless;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.ensias.miniprojet.Exceptions.UserNotFoundException;
import ma.ensias.miniprojet.Model.User;
import java.io.Serializable;
import java.util.List;
@Stateless
public class JpaUserService implements Serializable, UserService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "userPU")
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM AppUser u", User.class).getResultList();
    }

    @Override
    public User getLoggedUser() {
        User loggedUser = (User) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("loggedUser");
        if (loggedUser == null) {
            System.out.println("No logged-in user found in session.");
        } else {
            System.out.println("Logged-in user: " + loggedUser.getUsername());
        }
        return loggedUser;
    }


    @Override
    public void addUser(User user) {
        em.merge(user);

    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public User authenticate(String username, String password) throws UserNotFoundException {
        System.out.println("Authenticating user: " + username);
        try {
            User user = em.createQuery("SELECT u FROM AppUser u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            System.out.println("User found: " + user.getUsername());
            return user;
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new UserNotFoundException("Authentication failed: " + e.getMessage());
        }
    }
}