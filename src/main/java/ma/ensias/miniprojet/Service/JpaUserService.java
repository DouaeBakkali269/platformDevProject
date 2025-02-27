package ma.ensias.miniprojet.Service;

import jakarta.ejb.Stateless;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.ensias.miniprojet.Exceptions.UserNotFoundException;
import ma.ensias.miniprojet.Model.User;
import java.io.Serializable;
import java.util.List;

@Stateless // Stateless for automatic transaction management in Jakarta EE
public class JpaUserService implements Serializable, UserService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "userPU")
    private EntityManager em; // Injected automatically

    @Override
    public void addUser(User user) {
        em.persist(user);
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
    public User getLoggedUser() {
        return (User) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("loggedUser");
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM AppUser  u", User.class).getResultList();
    }

    @Override
    public User authenticate(String username, String password) throws UserNotFoundException {
        try {
            return em.createQuery("SELECT u FROM AppUser  u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            throw new UserNotFoundException("Authentication failed: " + e.getMessage());
        }
    }
}
