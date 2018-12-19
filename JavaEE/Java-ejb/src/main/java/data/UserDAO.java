package data;

import model.Indication;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.faces.component.UIInput.isEmpty;



@Stateless
public class UserDAO {


    @PersistenceContext(unitName = "examplePU")
    private EntityManager em;

    public boolean checkEnter (String email, String password)
    {
        if (isEmpty(email) || isEmpty(password))
            return false;

        User user = em.find(User.class, email);
        if (user == null)
            return false;

        if(password.equals(user.getPassword()))
            return true;


        return false;
    }

    public boolean addNewIndicator(Indication.IndicationType indicationType, BigDecimal indication, String date) throws ParseException
    {
        if(indicationType == null || indication == null)
            return false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        Indication newIndication = new Indication(indicationType, indication, dateFormat.parse(date));

        em.persist(newIndication);

        return true;

    }

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
