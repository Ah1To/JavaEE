package CDI;

import data.UserDAO;
import model.Indication;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Singleton
public class UserCDI implements Serializable {
    private String email;
    private String password;

    private List<User> users;


    @EJB
    private UserDAO userDAO;




    public void buttonAction(ActionEvent actionEvent) {
        if ((email.length() < 3 || email.length() > 11)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert email 4-12 char",  null);
            addMessage(message);
            return;
            }

        if ((password.length() < 5 || password.length() > 17)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert password 6-18 char",  null);
            addMessage(message);
            return;
        }

        if(userDAO.createNewUser(email, password)){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "You is registred " + email,  null);
            addMessage(message);
        }
        else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error with registration " + email,  null);
            addMessage(message);
        }
    }

    public void addMessage(FacesMessage message) {

        FacesContext.getCurrentInstance().addMessage(null, message);
    }



    public List<User> getAllUsers(){
        users = userDAO.getAllUsers();
        return users;
    }

    public List<Indication> getAllIndication() {
        return userDAO.getAllIndication();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
