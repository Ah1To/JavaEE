package CDI;


import data.UserDAO;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;

@Named
@Singleton
public class RegistrationCDI implements Serializable {
    private String email;
    private String password;


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

        if(userDAO.checkValidEmail(email)){
            if(userDAO.createNewUser(email, password)){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "You is registred " + email,  null);
                addMessage(message);
            }
            else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error with registration " + email,  null);
                addMessage(message);
            }
        }
        else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User is exist " + email,  null);
            addMessage(message);
        }

    }

    public void addMessage(FacesMessage message) {

        FacesContext.getCurrentInstance().addMessage(null, message);
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
