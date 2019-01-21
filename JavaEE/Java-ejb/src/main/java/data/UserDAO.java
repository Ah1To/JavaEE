package data;

import model.Indication;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;




@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager em;


    public boolean createNewUser(String email, String password)
    {

        if ((email.length() < 3 || email.length() > 11) || (password.length() < 5 || password.length() > 17))
            return false;
        User newUser = new User(email, password);
        Set<User.UserRole> role = new HashSet<>();
        role.add(User.UserRole.ROLE_USER);
        newUser.setRoleUser(role);
        em.persist(newUser);

        return true;
    }

    public boolean checkValidEmail(String email){
        Query query = em.createQuery("select u from User u where email = ?1");
        query.setParameter(1,email);

        List<User> validEmail = query.getResultList();

        if(validEmail.size() != 0)
            return false;
        else
            return true;
    }

    public boolean checkEnter(String email, String password){
        Query query = em.createQuery("select u from User u where email = ?1 and password = ?2");

        query.setParameter(1,email);
        query.setParameter(2, password);

        List<User> existUser = query.getResultList();

        if(existUser.size() != 0)
            return true;
        else
            return false;

    }

    public List<User> getAllUsers ()
    {
        Query query = em.createQuery("select u from User u"); // Создать Именнованый запрос
        return query.getResultList();
    }

    public List<Indication> getAllIndication() {
        Query query = em.createQuery("select i from Indication i");
        return query.getResultList();
    }





}
